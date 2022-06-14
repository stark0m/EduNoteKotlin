package com.example.edunotekotlin.ui.main

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Bundle
import android.os.FileUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edunotekotlin.R
import com.example.edunotekotlin.entities.MenuDrawable
import com.example.edunotekotlin.ui.MainActivity
import com.example.edunotekotlin.ui.main.mainpresenter.NoteMainPresenterImpl
import com.example.kotlineasynote.entities.OneNote
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.roundToInt


class MainFragment : Fragment(), ViewInterface {

    val presenter: NoteMainPresenterImpl by lazy { NoteMainPresenterImpl(this,requireContext()) }
//    val presenter: NoteMainPresenterImpl by lazy { NoteMainPresenterImpl(this) }
    lateinit var progressbar: ProgressBar
    lateinit var toolbar: MaterialToolbar
    var recyclerViewAdapter = RecyclerViewAdapter()
    lateinit var recyclerView: RecyclerView
    lateinit var floatButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachFragment(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            recyclerView = findViewById(R.id.id_recycler_view)
            floatButton = findViewById(R.id.floating_action_button)
            progressbar = findViewById(R.id.progress)
            toolbar = findViewById(R.id.fragment_main_toolbar)
        }

        addDrawerMenuIfExist()
        initRecycler()
        presenter.init()
        initCallBacks()
        initListeners()
        initContextMenu()



    }

    private fun initContextMenu() {
        registerForContextMenu(recyclerView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.context_menu,menu)
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.menu_context_delete ->{presenter.deleteNote(presenter.getSelectedNote()!!)
            recyclerViewAdapter.notifyItemRemoved(presenter.getSelectedNotePosition()!!)
                return true}
            R.id.menu_context_edit ->{presenter.updateNote(presenter.getSelectedNote()!!)
                return true}
        }
        return super.onContextItemSelected(item)
    }

    private fun addDrawerMenuIfExist() {
        if (requireActivity() is MenuDrawable) {
            (requireActivity() as MenuDrawable).setAppToolbar(toolbar)
        }
    }

    private fun initListeners() {
        floatButton.setOnClickListener() {
            presenter.addNote()
        }

        toolbar.setOnMenuItemClickListener() {
            when (it.itemId) {
                R.id.menu_add -> presenter.addNote()
                else -> {
                    true
                }
            }
            true
        }
    }

    private fun initCallBacks() {

        recyclerViewAdapter.clickedNote = object : RecyclerViewAdapter.ClickedNote {
            override fun onClicked(note: OneNote) {
                presenter.updateNote(note)
            }

            override fun onLongClicked(note: OneNote, position: Int) {

                presenter.setSelectedNote(note)
                presenter.setSelectedNotePosition(position)

            }



        }


    }

    private fun initRecycler() {
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.adapter = recyclerViewAdapter


        val myCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false


            @SuppressLint("UseCompatLoadingForDrawables")
            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                super.onChildDraw(
                    c, recyclerView, viewHolder,
                    dX, dY, actionState, isCurrentlyActive
                )

                c.clipRect(
                    viewHolder.itemView.width.toFloat() + dX,
                    viewHolder.itemView.top.toFloat(),
                    viewHolder.itemView.width.toFloat(),
                    viewHolder.itemView.bottom.toFloat()
                )

                /**
                 * iconSize- размер иконки отображаемой при свайпе
                 * trashBinIcon - иконка для отображения удаления заметки
                 */
                val iconSize = viewHolder.itemView.bottom - viewHolder.itemView.top
                val trashBinIcon = resources.getDrawable(
                    R.drawable.ic_baseline_delete_sweep_24,
                    null
                )
                val textMargin = resources.getDimension(R.dimen.text_margin)
                    .roundToInt()
                trashBinIcon.bounds = Rect(
                    viewHolder.itemView.width - iconSize,
                    viewHolder.itemView.top + textMargin,
                    viewHolder.itemView.width - textMargin,
                    viewHolder.itemView.bottom -
                            textMargin
                )

                /**
                 * отображаем иконку корзины если свайпнули заметку более чем на 1/3 экрана
                 */
                if (Math.abs(dX) > viewHolder.itemView.width / 3)
                    trashBinIcon.draw(c)
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {


                val position = viewHolder.adapterPosition

                presenter.deleteNote(recyclerViewAdapter.data.get(position))
                recyclerViewAdapter.data.removeAt(position)
                recyclerViewAdapter.notifyItemRemoved(position)


            }

        }

        val myHelper = ItemTouchHelper(myCallback)
        myHelper.attachToRecyclerView(recyclerView)
    }


    override fun onDetach() {
        super.onDetach()
        presenter.detouchFragment()
    }

    override fun redraw() {

        recyclerViewAdapter.notifyDataSetChanged()
    }

    override fun redrawRecyclerInPosition(position: Int) {

        recyclerViewAdapter.notifyItemChanged(position)
    }

    override fun writeNoteListToData(list: MutableList<OneNote>) {
        recyclerViewAdapter.data = list


    }

    override fun startLoading() {
        progressbar.visibility = View.VISIBLE

    }

    override fun loaded() {
        progressbar.visibility = View.GONE

    }

    override fun addNote(newNote: OneNote) {
        recyclerViewAdapter.data.add(0, newNote)
        recyclerViewAdapter.notifyItemChanged(0)
    }

    companion object

    fun newInstance(): MainFragment {
        val instance: MainFragment by lazy { MainFragment() }
        return instance
    }


}


