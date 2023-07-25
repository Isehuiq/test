package tw.com.test;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;

public class forexFragment extends Fragment {
    private View view;
    private RecyclerView mrecyclerView;
    private RecyclerView.LayoutManager mlayoutManager;
    private LinkedList<HashMap<String, String>> data;
    private MyAdapter myAdapter;
    private Spinner spinner;
    private EditText editText, editText1;
    private InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence charSequence, int start, int end, Spanned dest, int dstart, int dend) {
            for (int i = start; i < end; i++) {
                if (!Character.isDigit(charSequence.charAt(i))) {
                    return "";
                }
            }
            return null;
        }
    };

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public forexFragment() {
        // Required empty public constructor
    }

    public static forexFragment newInstance(String param1, String param2) {
        forexFragment fragment = new forexFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_forex, container, false);

        mrecyclerView = view.findViewById(R.id.recycleview);
        mrecyclerView.setHasFixedSize(true);
        mlayoutManager = new LinearLayoutManager(getActivity());
        mrecyclerView.setLayoutManager(mlayoutManager);
        doData(0);
        myAdapter = new MyAdapter();
        mrecyclerView.setAdapter(myAdapter);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mrecyclerView);
        spinner = view.findViewById(R.id.spinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.country,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parent, View view, int i, long l) {
                Log.v("Lin", i+" "+l);
//                String result = parent.getItemAtPosition(i).toString();
//                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                doData(i);
                myAdapter = new MyAdapter();
                mrecyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        editText = view.findViewById(R.id.input);
        editText.setFilters(new InputFilter[]{filter});
        editText1 = view.findViewById(R.id.output);
        editText1.setEnabled(false);
        // 更改第二個 EditText 的外觀以表示不可編輯狀態
        editText1.setFocusable(false);
        editText1.setFocusableInTouchMode(false);
        editText1.setTextColor(Color.GRAY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            editText1.setBackground(null);
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String input = editable.toString();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                    if(!input.isEmpty()) {
                        double number = Double.parseDouble(input);
                        double result = number * 30.6900;
                        editText1.setText(String.valueOf(result));
                    }
                    else {
                        editText1.setText("");
                    }
                }
            }
        });

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).CollectScrollDataOnViewPager2(mrecyclerView, "fortextFragment的recyclerView");
        }

        return view;
    }

    private  class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        class MyViewHolder extends RecyclerView.ViewHolder {
            public View itemView;
            public TextView country, buyin, buyout;

            public MyViewHolder(View v) {
                super(v);
                itemView = v;

                country = itemView.findViewById(R.id.country);
                buyin = itemView.findViewById(R.id.buyin);
                buyout = itemView.findViewById(R.id.buyout);
            }
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item2, parent, false);
            MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
            holder.country.setText(data.get(position).get("country"));
            holder.buyin.setText(data.get(position).get("buyin"));
            holder.buyout.setText(data.get(position).get("buyout"));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    private void doData(int n) {
        data = new LinkedList<>();
        if (n == 0) {
            HashMap<String, String> d = new HashMap<>();
            d.put("country", "美國USD");
            d.put("buyin", "30.5900");
            d.put("buyout", "30.6900");
            data.add(d);
            HashMap<String, String> d1 = new HashMap<>();
            d1.put("country", "港幣HKD");
            d1.put("buyin", "3.8750");
            d1.put("buyout", "3.9350");
            data.add(d1);
            HashMap<String, String> d2 = new HashMap<>();
            d2.put("country", "日幣JPY");
            d2.put("buyin", "0.2252");
            d2.put("buyout", "0.2302");
            data.add(d2);
            HashMap<String, String> d3 = new HashMap<>();
            d3.put("country", "歐元EUR");
            d3.put("buyin", "33.4800");
            d3.put("buyout", "33.8800");
            data.add(d3);
            HashMap<String, String> d4 = new HashMap<>();
            d4.put("country", "人民幣CNY");
            d4.put("buyin", "4.4120");
            d4.put("buyout", "4.4620");
            data.add(d4);
        }
        else if (n == 1) {
            HashMap<String, String> d = new HashMap<>();
            d.put("country", "美國USD");
            d.put("buyin", "30.5900");
            d.put("buyout", "30.6900");
            data.add(d);
        }
        else if (n == 2) {
            HashMap<String, String> d1 = new HashMap<>();
            d1.put("country", "港幣HKD");
            d1.put("buyin", "3.8750");
            d1.put("buyout", "3.9350");
            data.add(d1);
        }
        else if (n == 3) {
            HashMap<String, String> d2 = new HashMap<>();
            d2.put("country", "日幣JPY");
            d2.put("buyin", "0.2252");
            d2.put("buyout", "0.2302");
            data.add(d2);
        }
        else if (n == 4) {
            HashMap<String, String> d3 = new HashMap<>();
            d3.put("country", "歐元EUR");
            d3.put("buyin", "33.4800");
            d3.put("buyout", "33.8800");
            data.add(d3);
        }
        else if (n == 5) {
            HashMap<String, String> d4 = new HashMap<>();
            d4.put("country", "人民幣CNY");
            d4.put("buyin", "4.4120");
            d4.put("buyout", "4.4620");
            data.add(d4);
        }
    }
}