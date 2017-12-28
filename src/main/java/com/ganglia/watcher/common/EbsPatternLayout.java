package com.ganglia.watcher.common;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternParser;

/**
 * <p>
 * 提供日志格式化操作。
 * </p>
 * @version Revision: 1.0
 * @since Date: 2009-5-7 上午11:29:41
 */
public class EbsPatternLayout extends PatternLayout {

    public EbsPatternLayout(String pattern) {
        super(pattern);
    }

    public EbsPatternLayout() {
        super();
    }

    /**
     * 重写createPatternParser方法，返回PatternParser的子类
     */
    @Override
    protected PatternParser createPatternParser(String pattern) {
        return new EbsLogPatternParser(pattern);
    }
}
