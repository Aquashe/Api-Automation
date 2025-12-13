package com.context;

public class ContextHolder {
    private static final  ThreadLocal<ScenarioContext> context =
            ThreadLocal.withInitial(ScenarioContext::new);

    public static ScenarioContext getContext(){
        return context.get();
    }

    public static void clear(){
        context.remove();
    }
}
