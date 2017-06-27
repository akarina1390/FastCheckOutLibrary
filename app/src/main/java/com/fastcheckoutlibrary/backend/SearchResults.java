package com.fastcheckoutlibrary.backend;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.anakarinacarrocci.fastcheckoutlibrary.R;
import com.fastcheckoutlibrary.objects.Book;

import java.util.ArrayList;
import java.util.List;

public class SearchResults extends AppCompatActivity {
    private static ArrayList<Book> theBookList;
    private List<String> data = new ArrayList<String>();
    String specialBreaker = "  ";

    static void setBookList(ArrayList<Book> bookList) {
        theBookList = bookList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context ctx = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        ListView lv = (ListView) findViewById(R.id.listOfResults);
        //generateListContent(context);
        lv.setAdapter(new MyListAdaper(this, R.layout.activity_list_of_books_results, data));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                //viewFlashCardContent(context, index);
            }
        });
    }

    private List<String> generateList() {
        List<String> list = new ArrayList<>();
        /*for (Book book : theBookList) {
            list.add(book.getId() + specialBreaker + book.getName() + specialBreaker + book.getEdition()
            + specialBreaker + book.getLibraryName() + specialBreaker + book.getAddressName());
        }*/
        list.add("Sonny");
        return list;
    }

    private class MyListAdaper extends ArrayAdapter<String> {
        private int layout;
        private List<String> mObjects;

        private MyListAdaper(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }

        @Override
        public View getView(final int index, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.title = (TextView) convertView.findViewById(R.id.listItemText);
                //viewHolder.delete = (ImageButton) convertView.findViewById(R.id.imageButtonDeleteItem);
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            /*mainViewholder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (deleteRecord(data.get(index))) {
                        Toast.makeText(getContext(), "Item has been successfully removed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), HomeFlashCard.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), "Item has been not been removed", Toast.LENGTH_SHORT).show();
                    }
                }
            });*/

            mainViewholder.title.setText(getItem(index));

            return convertView;
        }
    }

    /*
    private class MyListAdaper extends ArrayAdapter<String> {
        private int layout;
        private List<String> mObjects;

        private MyListAdaper(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }

        @Override
        public View getView(final int index, View convertView, ViewGroup parent) {
            //String sonny = mObjects.get(index);
            //final String id = getItem(index).split(specialBreaker)[0];
            //final String name = getItem(index).split(specialBreaker)[1];
            //final String edition = getItem(index).split(specialBreaker)[2];
            //final String libName = getItem(index).split(specialBreaker)[3];
            //final String address = getItem(index).split(specialBreaker)[4];

            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.title = (TextView) convertView.findViewById(R.id.listItemText);
                viewHolder.reserve = (ImageButton) convertView.findViewById(R.id.imageButtonReserve);
                viewHolder.info = (ImageButton) convertView.findViewById(R.id.imageButtonInfo);
                convertView.setTag(viewHolder);
            }
            ViewHolder mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.reserve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            mainViewholder.info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
               //     AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
                //    alert.setMessage(libName + " (" + address + ")");
                 //   alert.setCancelable(true);
                  //  AlertDialog alertDialog = alert.create();
                   // alertDialog.show();
                }
            });

            //mainViewholder.title.setText(name + " - Edition: " + edition);

            return convertView;
        }
    }
*/
    private class ViewHolder {
        TextView title;
        ImageButton reserve;
        ImageButton info;
    }
}


