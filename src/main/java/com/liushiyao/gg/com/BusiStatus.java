package com.liushiyao.gg.com;

/**
 *返回参数错误码枚举
 */
public enum BusiStatus {

    SUCCESS(200, "success"), //成功

    BUSIERROR(4000, "BusiError"),       //程序报错

    BUSIFAIL(5001,"BUSIFAIL"),          //获取网络参数失败

    INVALID_SERVICE(199, "invalid service"),//服务不可用

    SERVEXCEPTION(5000, "service exception"),

    UNKNOWN(999, "unknown"),//未知错误


    PARAMETERILLEGAL(1444, "parameter illegal"),

    USERNOTEXISTS(1404, "user not exists"),

    NOTLIKEONESELF(1500, "you can't like yourself"),

    WEEKNOTWITHCASHTOWNUMS(1600, "only two withdrawals a week"),

    PRIVATEPHOTOSUPMAX(1700, "more than 8 albums"),

    ROOMRUNNING(1500, "room is running"),
    ROOMRCLOSED(1501, "room closed"),
    NOTHAVINGLIST(1600, "there is no list"),

    AUCTCURDOING(2100, "sb being auctioned"),

    AUCTCURLESSTHANMAXMONEY(2101, "auction less than max money"),
    AUCTCURYOURSELFERROR(2102, "you can't auct yourself"),
    PURSEMONEYNOTENOUGH(2103, "account money not enough"),
    DIAMONDNUMNOTENOUGH(2104, "diamond Number is not enough"),
    ORDERNOTEXISTS(3404, "order not exists"),
    ORDERFINISHED(3100, "order has finished"),

    SMSSENDERROR(4001, "send sms error"),
    PHONEINVALID(4002, "invalid mobile phone"),
    SMSCODEERROR(4003, "sms code error"),

    MICRONOTININVIATEDLIST(6000, "you are not in invited list"),
    MICRONUMLIMIT(60001, "micro number limit");

    private final int value;

    private final String reasonPhrase;

    private BusiStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }


    public int value() {
        return this.value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }


}
