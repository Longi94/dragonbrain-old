package in.dragonbra.dragonbrain.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ScheduledTask {

    private Object bean;

    private Method task;

    public ScheduledTask(Object bean, Method task) {
        this.bean = bean;
        this.task = task;
    }

    public void run() throws InvocationTargetException, IllegalAccessException {
        task.invoke(bean);
    }
}
