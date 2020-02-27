package com.doubleview.commons;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class Test {
    public static void main(String[] args) {
        
        String sql = "create table ###\n" +
                "(\n" +
                "    guid              varchar(36)                              not null\n" +
                "        constraint pk_###\n" +
                "            primary key,\n" +
                "    fault_guid        varchar(36)                              not null,\n" +
                "    user_guid         varchar(36)                              not null,\n" +
                "    user_new_id       bigint                                   not null,\n" +
                "    event_code        smallint                                 not null,\n" +
                "    judge_rule_code   varchar(16)                              not null,\n" +
                "    duty_side         smallint   default 0                     not null,\n" +
                "    real_duty_side    smallint   default 0                     not null,\n" +
                "    bad_order_level   varchar(8) default ''::character varying not null,\n" +
                "    fake_level        varchar(8) default ''::character varying not null,\n" +
                "    reason            varchar(50),\n" +
                "    order_id          varchar(36),\n" +
                "    bike_no           varchar(11),\n" +
                "    fault_create_time timestamp                                not null,\n" +
                "    create_time       timestamp                                not null,\n" +
                "    update_time       timestamp                                not null,\n" +
                "    open_lock_time    timestamp\n" +
                ");\n" +
                "\n" +
                "comment on table ### is '判责记录';\n" +
                "comment on column ###.guid is '数据密级S2,主键';\n" +
                "comment on column ###.fault_guid is '数据密级S2,报障记录guid';\n" +
                "comment on column ###.user_guid is '数据密级S2,用户guid';\n" +
                "comment on column ###.user_new_id is '数据密级S2,新用户id';\n" +
                "comment on column ###.event_code is '数据密级S2,异常事件码,11锁未开已计费_12锁关上仍计费_13锁关不上';\n" +
                "comment on column ###.judge_rule_code is '数据密级S2,判责规则编号';\n" +
                "comment on column ###.duty_side is '数据密级S2,责任方,1哈啰公司责任_2用户责任';\n" +
                "comment on column ###.real_duty_side is '数据密级S2,此次真实责任方,1哈啰公司责任_2用户责任';\n" +
                "comment on column ###.bad_order_level is '数据密级S2,坏单率定级';\n" +
                "comment on column ###.fake_level is '数据密级S2,虚假率定级';\n" +
                "comment on column ###.reason is '数据密级S2,原因';\n" +
                "comment on column ###.order_id is '数据密级S2,订单号-冗余存储';\n" +
                "comment on column ###.bike_no is '数据密级S2,车辆编号-冗余存储';\n" +
                "comment on column ###.fault_create_time is '数据密级S2,还车异常上报时间-冗余存储';\n" +
                "comment on column ###.create_time is '数据密级S2,创建时间';\n" +
                "comment on column ###.update_time is '数据密级S2,更新时间';\n" +
                "comment on column ###.open_lock_time is '数据密级C2，开锁时间';\n" +
                "\n" +
                "create index idx_###_fault_guid  on ### (fault_guid);\n" +
                "create index idx_###_user_new_id  on ### (user_new_id);\n" +
                "create index idx_###_order_id on ### (order_id);\n" +
                "create index idx_###_duty_side on ### (duty_side);\n" +
                "create index idx_###_event_code on ### (event_code);\n" +
                "\n" +
                "GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE ### TO bike_themis_rw;\n" +
                "GRANT SELECT ON TABLE ### TO bike_themis_ro;";

        IntStream.range(0, 10).forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(sql.replaceAll("###", "t_judge_" + value));
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
            }
        });
    }
}
