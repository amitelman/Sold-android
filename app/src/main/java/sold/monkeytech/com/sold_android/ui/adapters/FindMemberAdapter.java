//package sold.monkeytech.com.sold_android.ui.adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.LinearLayout;
//
//import com.mid.mid.R;
//import com.mid.mid.framework.models.member.Member;
//import com.mid.mid.ui.fontable_views.FontableTextView;
//import com.monkeytechy.framework.interfaces.TAction;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by monkey on 25/06/2015.
// */
//public class FindMemberAdapter extends BaseAdapter {
//    private final TAction<Member> memberTAction;
//    private Context context;
//    private List<Member> members;
//
//    public FindMemberAdapter(Context context, List<Member> members, TAction<Member> memberTAction) {
//        this.context = context;
//        this.memberTAction = memberTAction;
//        if(members != null)
//            this.members = members;
//    }
//
//    public void updateList(List<Member> members){
//        if(this.members == null)
//            this.members = new ArrayList<>();
//        this.members.clear();
//        if(members != null)
//            this.members.addAll(members);
//        notifyDataSetChanged();
//    }
//
//
//    @Override
//    public int getCount() {
//        if(members != null)
//            return members.size();
//        return 0;
//    }
//
//    @Override
//    public Member getItem(int i) {
//        return members.get(i);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    public static class BaseViewHolder{
//        LinearLayout mainLayout;
//        FontableTextView companyName;
//        FontableTextView name;
//        FontableTextView email;
//    }
//
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        BaseViewHolder baseViewHolder = new BaseViewHolder();
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//            if (convertView == null){
//                convertView = inflater.inflate(R.layout.find_member_item, parent, false);
//                baseViewHolder.mainLayout = (LinearLayout) convertView.findViewById(R.id.findMmberMainLayout);
//                baseViewHolder.companyName = (FontableTextView) convertView.findViewById(R.id.findMemberItemCompanyName);
//                baseViewHolder.name = (FontableTextView) convertView.findViewById(R.id.findMemberItemName);
//                baseViewHolder.email = (FontableTextView) convertView.findViewById(R.id.findMemberItemMail);
//
//                convertView.setTag(baseViewHolder);
//            }else {
//                baseViewHolder = (BaseViewHolder) convertView.getTag();
//            }
//
//
//        final Member member = getItem(position);
//
//        baseViewHolder.companyName.setText(member.getCompany());
//        baseViewHolder.name.setText(member.getFirstName() + " " + member.getLastName());
//        baseViewHolder.email.setText(member.getEmail());
//        baseViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(memberTAction != null)
//                    memberTAction.execute(member);
//            }
//        });
//
//        return convertView;
//    }
//
//}
