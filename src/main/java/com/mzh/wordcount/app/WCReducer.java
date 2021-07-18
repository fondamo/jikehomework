package com.mzh.wordcount.app;

import java.io.IOException;

import com.mzh.wordcount.bean.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WCReducer extends Reducer<Text, FlowBean, Text, FlowBean> {

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context)throws IOException, InterruptedException {

        long sum_upFlow = 0;
        long sum_downFlow = 0;

        for (FlowBean flowBean : values) {
            sum_upFlow += flowBean.getUpFlow();
            sum_downFlow += flowBean.getDownFlow();
        }

        FlowBean resultBean = new FlowBean(sum_upFlow, sum_downFlow);

        context.write(key, resultBean);
    }
}