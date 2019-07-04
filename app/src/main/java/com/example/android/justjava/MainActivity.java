package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {


    int numberofcoffees=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increasequantity(View view)
    {numberofcoffees+=1;
     if(numberofcoffees>100)
         numberofcoffees=100;
     display(numberofcoffees);}

     private int calculateprice(boolean chocolate, boolean whipped_cream)
     {   int addons=0;
         if(chocolate) addons+=(2*numberofcoffees);
         if(whipped_cream) addons+=numberofcoffees;
         return (numberofcoffees*5 + addons);}

     private String getname()
     {EditText name_text= (EditText)findViewById(R.id.name_text);
      String name=name_text.getText().toString();
      return name;
     }

     private String ordersummary(int price, boolean checked_state_whipped_cream, boolean checked_state_chocolate)
     {return ("Name: " + getname() + "\nQuantity: " + numberofcoffees + "\nAdd Whipped cream? " + checked_state_whipped_cream +
         "\nAdd Chocolate? " + checked_state_chocolate + "\nTotal: Rs" + price + "\nThank you!");}

    public void submitOrder(View view) {
        CheckBox whipped_cream = (CheckBox) findViewById(R.id.whipped_cream);
        boolean checked_state_whipped_cream = whipped_cream.isChecked();
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        boolean checked_state_chocolate = chocolate.isChecked();
        int price = calculateprice(checked_state_chocolate, checked_state_whipped_cream);
        String pricemessage = ordersummary(price, checked_state_whipped_cream, checked_state_chocolate);


        Intent mailClient = new Intent(Intent.ACTION_SENDTO);
        mailClient.setData(Uri.parse("mailto:"));
        mailClient.putExtra(Intent.EXTRA_TEXT, pricemessage);
        mailClient.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.summary));
        if (mailClient.resolveActivity(getPackageManager()) != null) {
            startActivity(mailClient);
        }
    }
    public void decreasequantity(View view)
    {numberofcoffees-=1;
       if (numberofcoffees<1)
           numberofcoffees=1;
        display(numberofcoffees);}


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView)
                findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */

}
