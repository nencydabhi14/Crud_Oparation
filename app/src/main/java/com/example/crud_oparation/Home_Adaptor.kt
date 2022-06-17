package com.example.crud_oparation

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView

class Home_Adaptor(val mainActivity: MainActivity, val l1: ArrayList<ModalData>) :
    RecyclerView.Adapter<Home_Adaptor.ViewData>() {

    class ViewData(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var id_txt = itemView.findViewById<TextView>(R.id.id_txt)
        var amount_txt = itemView.findViewById<TextView>(R.id.amount_txt)
        var deatils_txt = itemView.findViewById<TextView>(R.id.deatils_txt)
        var date_txt = itemView.findViewById<TextView>(R.id.date_txt)
        var delete_img = itemView.findViewById<ImageView>(R.id.delete_img)
        var edit_img = itemView.findViewById<ImageView>(R.id.edit_img)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        var view = LayoutInflater.from(mainActivity).inflate(R.layout.item_design, parent, false)
        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.id_txt.text = l1.get(position).id
        holder.amount_txt.text = l1.get(position).amount
        holder.deatils_txt.text = l1.get(position).details
        holder.date_txt.text = l1.get(position).date

        holder.delete_img.setOnClickListener {
            DBHelper(mainActivity).DelateData(l1[position].id)
            var l1 = DBHelper(mainActivity).ReadData()
            mainActivity.setUpRV(l1)
        }

        holder.edit_img.setOnClickListener {
            updateDialog(position)
        }
    }

    override fun getItemCount(): Int {
        return l1.size
    }
    fun updateDialog(position: Int){

        var dialog = Dialog(mainActivity)
        dialog.setContentView(R.layout.item_dialod)
        dialog.show()

        var customer_name_edt_d = dialog.findViewById<EditText>(R.id.customer_name_edt_d)
        var customer_no_edt_d = dialog.findViewById<EditText>(R.id.customer_no_edt_d)
        var amount_edt_d = dialog.findViewById<EditText>(R.id.amount_edt_d)
        var details_edt_d = dialog.findViewById<EditText>(R.id.details_edt_d)
        var date_edt_d = dialog.findViewById<EditText>(R.id.date_edt_d)
        var submit_btn_d = dialog.findViewById<Button>(R.id.submit_btn_d)

        submit_btn_d.setOnClickListener {
            DBHelper(mainActivity).UpDateData((l1)[position].id,amount_edt_d.text.toString(),details_edt_d.text.toString(),date_edt_d.text.toString())
            var l1 = DBHelper(mainActivity).ReadData()
            mainActivity.setUpRV(l1)

            dialog.dismiss()
        }
    }
}


