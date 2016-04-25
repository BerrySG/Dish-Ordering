package hk.edu.uic.dishordering.Model.MemberSubsystem;

public class Member {

    private String mName;
    private int mPhone;
    private Level mLevel;
    private int mExpendAmount;
    private float mDiscount;

    public Member(String name, int phone) {
        mName = name;
        mPhone = phone;

        mExpendAmount = 0;
        setLevel(Level.BRONZE);
    }

    public void increaseExpendAmount(int amount) {
        mExpendAmount += amount;
        if (mExpendAmount >= 500 && mExpendAmount < 1000) {
            setLevel(Level.SILVER);
        } else if (mExpendAmount >= 1000) {
            setLevel(Level.GOLD);
        }
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getPhone() {
        return mPhone;
    }

    public void setPhone(int phone) {
        mPhone = phone;
    }

    public Level getLevel() {
        return mLevel;
    }

    public void setLevel(Level level) {
        mLevel = level;

        switch (mLevel) {
            case BRONZE:
                mDiscount = 0.9f;
                break;
            case SILVER:
                mDiscount = 0.8f;
                break;
            case GOLD:
                mDiscount = 0.7f;
                break;
            default:
                break;
        }
    }

    public int getExpendAmount() {
        return mExpendAmount;
    }

    public float getDiscount() {
        return mDiscount;
    }

    public void setDiscount(float discount) {
        mDiscount = discount;
    }
}
