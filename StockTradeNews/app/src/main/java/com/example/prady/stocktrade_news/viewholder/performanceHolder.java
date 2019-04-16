package com.example.prady.stocktrade_news.viewholder;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prady.stocktrade_news.R;

import org.w3c.dom.Text;

public class performanceHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView companyname;
    TextView buyprice;
    TextView sellprice;
    TextView gainPercent;
    TextView gainUSD;
    ImageView imageArrow;
    public  void setCompanyname(String _companyName){
        this.companyname.setText(_companyName);
    }
    public  void setBuyprice(String _buy){
        this.buyprice.setText(_buy);
    }
    public void setSellprice(String _sell){
        this.sellprice.setText(_sell);
    }
    public void setGainPercent(String _gain){
        this.gainPercent.setText(_gain);
    }
    public void setGainUSD(String _gain){
        this.gainUSD.setText(_gain);
    }
    public void setImageArrow(String _imge){
        this.imageArrow.setImageURI(Uri.parse(_imge));
    }
    public void setImageRed(){
        this.imageArrow.setColorFilter(Color.RED);
    }
    public void setImageGreen(){
        this.imageArrow.setColorFilter(Color.GREEN);
    }
    public performanceHolder(View itemView){
        super(itemView);
        companyname = itemView.findViewById(R.id.companyname);
        buyprice = itemView.findViewById(R.id.buyprice);
        sellprice = itemView.findViewById(R.id.sellprice);
        gainPercent = itemView.findViewById(R.id.gainprice);
        gainUSD = itemView.findViewById(R.id.gainusd);
        imageArrow = itemView.findViewById(R.id.imagearrow);
        imageArrow.setImageURI(Uri.parse("android.resource://com.example.prady.stocktrade_news/" + R.drawable.ic_keyboard_arrow_up_black_24dp));
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
