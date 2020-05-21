/*
 * Copyright (c) 2012, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package java.util.stream;

import java.util.Spliterator;

/**
 * An operation in a stream pipeline that takes a stream as input and produces
 * a result or side-effect.  A {@code TerminalOp} has an input type and stream
 * shape（流形状）, and a result type.  A {@code TerminalOp} also has a set of
 * <em>operation flags</em> that describes how the operation processes elements
 * of the stream (such as short-circuiting or respecting encounter order; see
 * {@link StreamOpFlag}).
 *
 * <p>A {@code TerminalOp} must provide a sequential and parallel implementation
 * of the operation relative to a given stream source and set of intermediate
 * operations.
 * TerminalOp 必须提供相对于给定流源和中间操作集的操作的串行和并行实现
 *
 * @param <E_IN> the type of input elements
 * @param <R>    the type of the result
 * @since 1.8
 */
interface TerminalOp<E_IN, R> {
    /**
     * Gets the shape of the input type of this operation.
     *
     * @implSpec The default returns {@code StreamShape.REFERENCE}.
     *
     * @return StreamShape of the input type of this operation
     */
    default StreamShape inputShape() { return StreamShape.REFERENCE; }

    /**
     * Gets the stream flags of the operation.  Terminal operations may set a
     * limited subset of the stream flags defined in {@link StreamOpFlag}, and
     * these flags are combined with the previously combined stream and
     * intermediate operation flags for the pipeline.
     *
     * 获取操作的流标志。终止操作可以设置 StreamOpFlag 中定义的流标志的一个有限子集，
     * 这些标志与之前合并的流和管道的中间操作标志相结合。
     *
     * @implSpec The default implementation returns zero.
     *
     * @return the stream flags for this operation
     * @see StreamOpFlag
     */
    default int getOpFlags() { return 0; }

    /**
     * Performs a parallel evaluation of the operation using the specified
     * {@code PipelineHelper}, which describes the upstream intermediate
     * operations.
     *
     * @implSpec The default performs a sequential evaluation of the operation
     * using the specified {@code PipelineHelper}.
     *
     * @param helper the pipeline helper
     * @param spliterator the source spliterator
     * @return the result of the evaluation
     */
    /**
     * 并行操作，默认串行
     */
    default <P_IN> R evaluateParallel(PipelineHelper<E_IN> helper,
                                      Spliterator<P_IN> spliterator) {
        if (Tripwire.ENABLED)
            Tripwire.trip(getClass(), "{0} triggering TerminalOp.evaluateParallel serial default");
        return evaluateSequential(helper, spliterator);
    }

    /**
     * Performs a sequential evaluation of the operation using the specified
     * {@code PipelineHelper}, which describes the upstream intermediate
     * operations.
     *
     * @param helper the pipeline helper
     * @param spliterator the source spliterator
     * @return the result of the evaluation
     */
    /**
     * 顺序执行所有中间操作
     * @param helper 持有上游中间操作
     * @param spliterator 源
     */
    <P_IN> R evaluateSequential(PipelineHelper<E_IN> helper,
                                Spliterator<P_IN> spliterator);
}
