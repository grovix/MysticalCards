package dcn.spbstu.mysticalcards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class BoxActivity extends AppCompatActivity implements View.OnClickListener {

    Button back;
    int box = 0;

    Card[] cards;// = new Card[Storage.cards_.size()];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box);

        cards = new Card[Storage.cards_.size()];

        box = getIntent().getIntExtra("num", 0);
        int count = 0;
        for (int i = 0; i < Storage.cards_.size(); i++){
            if (Storage.cards_.get(i).getBox() == box){
                count++;
            }
        }

        String[] arr = new String[count];
        int j = 0;
        for (int i = 0; i < Storage.cards_.size(); i++) {
            if (Storage.cards_.get(i).getBox() == box) {
                arr[j] = Storage.cards_.get(i).getEn() + " - " + Storage.cards_.get(i).getRu();
                cards[j] = Storage.cards_.get(i);
                j++;
            }
        }
        ListView lv = (ListView) findViewById(R.id.lv_2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arr);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showPopupMenu(view, position);
            }
        });

        back = (Button) findViewById(R.id.back2);
        back.setOnClickListener(this);
    }

    private void showPopupMenu(final View v, final int position) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        final Intent intent1 = new Intent(this, EditCardActivity.class);
        popupMenu.inflate(R.menu.menu_box);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu1:
                        int k = 0;
                        int d = 0;
                        for(int i = 0; i < cards.length; i++){
                            if(cards[i].getBox() == box){
                                k++;
                            }
                            if(k - 1 == position){
                                d = i;
                            }
                        }
                        Storage.cards_.remove(cards[d]);
                        Forms.forms_.remove(cards[d].en_);
                        Toast.makeText(getApplicationContext(),
                                "Слово удалено",
                                Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intent = getIntent();
                        startActivity(intent);
                        return true;
                    case R.id.menu2:
                        int k1 = 0;
                        int d1 = 0;
                        for(int i = 0; i < cards.length; i++){
                            if(cards[i].getBox() == box){
                                k1++;
                            }
                            if(k1 - 1 == position){
                                d1 = i;
                            }
                        }
                        intent1.putExtra("position", d1);
                        intent1.putExtra("en", Storage.cards_.get(d1).getEn().toString());
                        intent1.putExtra("ru", Storage.cards_.get(d1).getRu().toString());
                        startActivity(intent1);
                        finish();
                        return true;
                    default:
                        return false;
                }
            }
        });

        popupMenu.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back2:
                finish();
                break;
            default:
                break;
        }
    }
}
