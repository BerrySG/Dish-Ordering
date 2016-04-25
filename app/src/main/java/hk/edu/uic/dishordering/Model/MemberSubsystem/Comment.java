package hk.edu.uic.dishordering.Model.MemberSubsystem;

import hk.edu.uic.dishordering.Model.DishSubsystem.Dish;

public class Comment {

    private String mContent;
    private Dish mDish;
    private Member mMember;

    public Comment(String content, Dish dish, Member member) {
        mContent = content;
        mDish = dish;
        mMember = member;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public Dish getDish() {
        return mDish;
    }

    public void setDish(Dish dish) {
        mDish = dish;
    }

    public Member getMember() {
        return mMember;
    }

    public void setMember(Member member) {
        mMember = member;
    }

}
