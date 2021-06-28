package com.jorge.squareblur;

/**
 * Created by Jorge on 6/28/21.
 */

public final class AlwaysAsyncPolicy implements AsyncPolicy {

    @Override
    public boolean shouldAsync(boolean isRenderScript, long computation) {
        return true;
    }

    @Override
    public void putSampleData(boolean isRenderScript, long computation, long timeInNanos) {
        // does nothing
    }
}
