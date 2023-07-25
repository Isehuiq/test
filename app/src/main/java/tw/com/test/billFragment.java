package tw.com.test;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;

public class billFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView, recyclerViewForConsumerDetails;
    private RecyclerView.LayoutManager layoutManager;   //ConsumerDetails
    private LinkedList<HashMap<String, Integer>> linkedListForCreditCard;
    private LinkedList<HashMap<String, String>> linkedListForConsumerDetails;
    private MyAdapter myAdapter;
    private ConsumerDetailsAdapter consumerDetailsAdapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    public billFragment() {
        // Required empty public constructor
    }
    public static billFragment newInstance(String param1, String param2) {
        billFragment fragment = new billFragment();
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
        view = inflater.inflate(R.layout.fragment_bill, container, false);

        initCreditCardRecycleView();

        recyclerView = view.findViewById(R.id.creditCardRecycleView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager abc = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(abc);
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.addItemDecoration(new CirclePagerIndicatorDecoration());

        recyclerViewForConsumerDetails =view.findViewById(R.id.consumerDetailsRecycleView);
        recyclerViewForConsumerDetails.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewForConsumerDetails.setLayoutManager(layoutManager);
        consumerDetailsAdapter = new ConsumerDetailsAdapter();
        recyclerViewForConsumerDetails.setAdapter(consumerDetailsAdapter);
        ShowConsumerDetail();

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).CollectScrollDataOnViewPager2(recyclerView, "billFragment的信用卡recyclerView");
        }

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).CollectScrollDataOnViewPager2(recyclerViewForConsumerDetails, "billFragment的餐廳recyclerView");
        }

        return view;
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        class MyViewHolder extends RecyclerView.ViewHolder {
            public View itemView;
            public ImageView imageView;
            public MyViewHolder(View v) {
                super(v);
                itemView = v;

                imageView = itemView.findViewById(R.id.creditCardImageView);
            }
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
            holder.imageView.setImageResource(linkedListForCreditCard.get(position).get("image"));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewForConsumerDetails.setAdapter(consumerDetailsAdapter);
                    switch (position) {
                        case 0:
                            ShowConsumerDetail();
                            break;
                        case 1:
                            ShowConsumerDetail1();
                            break;
                        case 2:
                            ShowConsumerDetail2();
                            break;
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return linkedListForCreditCard.size();
        }
    }
    public class ConsumerDetailsAdapter extends RecyclerView.Adapter<ConsumerDetailsAdapter.MyViewHolder> {
        class MyViewHolder extends RecyclerView.ViewHolder {
            public View itemView;
            public TextView date;
            public TextView place;
            public TextView cost;
            public MyViewHolder(View v) {
                super(v);
                itemView = v;

                date = itemView.findViewById(R.id.Date);
                place = itemView.findViewById(R.id.Place);
                cost = itemView.findViewById(R.id.Cost);
            }
        }

        @NonNull
        @Override
        public ConsumerDetailsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item1, parent, false);
            MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull ConsumerDetailsAdapter.MyViewHolder holder, int position) {
            holder.date.setText(linkedListForConsumerDetails.get(position).get("Date"));
            holder.place.setText(linkedListForConsumerDetails.get(position).get("Place"));
            holder.cost.setText(linkedListForConsumerDetails.get(position).get("Cost"));
        }

        @Override
        public int getItemCount() {
            return linkedListForConsumerDetails.size();
        }
    }
    public void initCreditCardRecycleView() {
        linkedListForCreditCard = new LinkedList<>();
        HashMap<String, Integer> d0 = new HashMap<>();
        d0.put("image", R.drawable.visa);
        linkedListForCreditCard.add(d0);

        HashMap<String, Integer> d1 = new HashMap<>();
        d1.put("image", R.drawable.mastercard);
        linkedListForCreditCard.add(d1);

        HashMap<String, Integer> d2 = new HashMap<>();
        d2.put("image", R.drawable.paypal);
        linkedListForCreditCard.add(d2);
    }
    public void ShowConsumerDetail() {
        linkedListForConsumerDetails= new LinkedList<>();
        HashMap<String, String> d0 = new HashMap<>();
        d0.put("Date", "2023/03/14");
        d0.put("Place", "好市多");
        d0.put("Cost", "$1000");
        linkedListForConsumerDetails.add(d0);
        HashMap<String, String> d1 = new HashMap<>();
        d1.put("Date", "2023/03/15");
        d1.put("Place", "翁媽媽便當");
        d1.put("Cost", "$100");
        linkedListForConsumerDetails.add(d1);
        HashMap<String, String> d2 = new HashMap<>();
        d2.put("Date", "2023/03/16");
        d2.put("Place", "新光三越");
        d2.put("Cost", "$1500");
        linkedListForConsumerDetails.add(d2);
        HashMap<String, String> d3 = new HashMap<>();
        d3.put("Date", "2023/03/17");
        d3.put("Place", "IKEA");
        d3.put("Cost", "$2500");
        linkedListForConsumerDetails.add(d3);
        HashMap<String, String> d4 = new HashMap<>();
        d4.put("Date", "2023/03/21");
        d4.put("Place", "星巴克");
        d4.put("Cost", "$200");
        linkedListForConsumerDetails.add(d4);
        HashMap<String, String> d5 = new HashMap<>();
        d5.put("Date", "2023/03/29");
        d5.put("Place", "麥當勞");
        d5.put("Cost", "$400");
        linkedListForConsumerDetails.add(d5);
        HashMap<String, String> d6 = new HashMap<>();
        d6.put("Date", "2023/04/01");
        d6.put("Place", "森川丼丼");
        d6.put("Cost", "$120");
        linkedListForConsumerDetails.add(d6);
        HashMap<String, String> d7 = new HashMap<>();
        d7.put("Date", "2023/04/03");
        d7.put("Place", "阿娥老店豆花");
        d7.put("Cost", "$60");
        linkedListForConsumerDetails.add(d7);
    }
    public void ShowConsumerDetail1() {
        linkedListForConsumerDetails= new LinkedList<>();
        HashMap<String, String> d0 = new HashMap<>();
        d0.put("Date", "2023/05/01");
        d0.put("Place", "美滿懷舊餐廳");
        d0.put("Cost", "$1000");
        linkedListForConsumerDetails.add(d0);
        HashMap<String, String> d1 = new HashMap<>();
        d1.put("Date", "2023/5/03");
        d1.put("Place", "巷子裡的小餐館");
        d1.put("Cost", "$100");
        linkedListForConsumerDetails.add(d1);
        HashMap<String, String> d2 = new HashMap<>();
        d2.put("Date", "2023/05/06");
        d2.put("Place", "山玥左岸餐廳");
        d2.put("Cost", "$1500");
        linkedListForConsumerDetails.add(d2);
        HashMap<String, String> d3 = new HashMap<>();
        d3.put("Date", "2023/05/08");
        d3.put("Place", "白話文學");
        d3.put("Cost", "$2500");
        linkedListForConsumerDetails.add(d3);
        HashMap<String, String> d4 = new HashMap<>();
        d4.put("Date", "2023/05/10");
        d4.put("Place", "華亭休閒餐廳");
        d4.put("Cost", "$200");
        linkedListForConsumerDetails.add(d4);
        HashMap<String, String> d5 = new HashMap<>();
        d5.put("Date", "2023/05/14");
        d5.put("Place", "萬家庄豬腳");
        d5.put("Cost", "$400");
        linkedListForConsumerDetails.add(d5);
        HashMap<String, String> d6 = new HashMap<>();
        d6.put("Date", "2023/05/18");
        d6.put("Place", "Fun 心享餐廳");
        d6.put("Cost", "$120");
        linkedListForConsumerDetails.add(d6);
        HashMap<String, String> d7 = new HashMap<>();
        d7.put("Date", "2023/05/24");
        d7.put("Place", "貝納義式料理");
        d7.put("Cost", "$60");
        linkedListForConsumerDetails.add(d7);
    }
    public void ShowConsumerDetail2() {
        linkedListForConsumerDetails= new LinkedList<>();
        HashMap<String, String> d0 = new HashMap<>();
        d0.put("Date", "2023/06/01");
        d0.put("Place", "努逗風味館");
        d0.put("Cost", "$1000");
        linkedListForConsumerDetails.add(d0);
        HashMap<String, String> d1 = new HashMap<>();
        d1.put("Date", "2023/06/03");
        d1.put("Place", "1314異國料理餐廳");
        d1.put("Cost", "$100");
        linkedListForConsumerDetails.add(d1);
        HashMap<String, String> d2 = new HashMap<>();
        d2.put("Date", "2023/06/06");
        d2.put("Place", "東里流芳巷子料理");
        d2.put("Cost", "$1500");
        linkedListForConsumerDetails.add(d2);
        HashMap<String, String> d3 = new HashMap<>();
        d3.put("Date", "2023/06/08");
        d3.put("Place", "雲來窯烤雞");
        d3.put("Cost", "$2500");
        linkedListForConsumerDetails.add(d3);
        HashMap<String, String> d4 = new HashMap<>();
        d4.put("Date", "2023/06/10");
        d4.put("Place", "心宜草堂");
        d4.put("Cost", "$200");
        linkedListForConsumerDetails.add(d4);
        HashMap<String, String> d5 = new HashMap<>();
        d5.put("Date", "2023/06/14");
        d5.put("Place", "嘉義新象園日式餐廳");
        d5.put("Cost", "$400");
        linkedListForConsumerDetails.add(d5);
        HashMap<String, String> d6 = new HashMap<>();
        d6.put("Date", "2023/06/18");
        d6.put("Place", "三采田美味廚坊");
        d6.put("Cost", "$120");
        linkedListForConsumerDetails.add(d6);
        HashMap<String, String> d7 = new HashMap<>();
        d7.put("Date", "2023/06/24");
        d7.put("Place", "東園軒園林餐廳");
        d7.put("Cost", "$60");
        linkedListForConsumerDetails.add(d7);
    }

}