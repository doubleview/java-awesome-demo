package com.doubleview;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

public class SentinelTest {

    public static void main(String[] args) throws InterruptedException {
        // 配置规则.
//        initFlowRules();

        while (true) {
            ContextUtil.enter("my_entrance", "appA");
            try {
                Entry node1 = SphU.entry("node1");
                System.out.println("node1");

                Entry node2 = SphU.entry("node2");
                System.out.println("node2");

                Entry node3 = SphU.entry("node3");
                System.out.println("node3");

                if (node3 != null) {
                    node3.exit();
                }

                if (node2 != null) {
                    node2.exit();
                }

                if (node1 != null) {
                    node1.exit();
                }
                ContextUtil.exit();
            } catch (BlockException e) {
                System.out.println("blocked!");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("node3");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
//        rule.setStrategy()
        // Set limit QPS to 20.
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
