package rwbykit.flowableTemp.core.customized.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.runtime.parameter.TaskParameter;
import rwbykit.flowableTemp.Constants;
import rwbykit.flowableTemp.FlowableException;
import rwbykit.flowable.engine.Result;
import rwbykit.flowableTemp.core.ProcessConfigContext;
import rwbykit.flowableTemp.core.ProcessConstants;
import rwbykit.flowableTemp.core.customized.ObjectResult;
import rwbykit.flowable.engine.runtime.runner.Runner;
import rwbykit.flowable.engine.factory.RunnerFactory;
import rwbykit.flowableTemp.core.util.FlowableHelper;
import rwbykit.flowableTemp.model.enumeration.ExecuteMode;

import java.util.Objects;

/**
 * 任务执行
 * 
 * @author Cytus_
 * @since 2018年12月17日 上午10:42:48
 * @version 1.0
 */
public class TaskRunner implements Runnable {
    
    private static final Logger logger = LoggerFactory.getLogger(TaskRunner.class);
    
    private TaskParameter taskParameter;
    
    private ExecuteMode executeMode;
    
    private String runValue;

    private String taskInstanceId;
    
    private Result<?> result;
    
    private String taskStatus = ProcessConstants.STATUS_RUNNING;

    TaskRunner(String taskInstanceId, TaskParameter taskParameter, ExecuteMode executeMode, String runValue) {
        this.taskParameter = taskParameter;
        this.executeMode = executeMode;
        this.runValue = runValue;
        this.taskInstanceId = taskInstanceId;
    }

    public static TaskRunnerBuilder builder() {
        return new TaskRunnerBuilder();
    }


    @Override
    @SuppressWarnings("unchecked")
    public void run() {

        /*Observable.fromCallable((Callable<Result<?>>) () -> {
            Runner<TaskParameter, Result<?>> runner = RunnerFactory.factory().getRunner(executeMode);
            Result<?> runnerResult = runner.run(runValue, taskParameter);
            if (!runnerResult.isSuccess()) {
                throw new FlowableException(runnerResult.errorCode(), runnerResult.errorMessage());
            }
            return runnerResult;
        });*/
        
        String errorMsg = null;
        try {
            logger.debug("任务实例[{}]执行开始", taskInstanceId);
            Runner<TaskParameter, Result<?>> runner = RunnerFactory.factory().getRunner(executeMode);
            Result<?> runnerResult = runner.run(runValue, taskParameter);
            if (!runnerResult.isSuccess()) {
                throw new FlowableException(runnerResult.errorCode(), runnerResult.errorMessage());
            }
            result = runnerResult;
            taskStatus = ProcessConstants.STATUS_END;
            logger.debug("任务实例[{}]执行结束", taskInstanceId);
        } catch (FlowableException e) {
            logger.error(FlowableHelper.formatMessage("任务实例[{}]执行当前任务出现异常!", taskInstanceId), e);
            errorMsg = e.getMessage();
            taskStatus = ProcessConstants.STATUS_EXCEPTION;
            result = ObjectResult.createFailure(e.getErrorCode(), errorMsg);
        } catch (Exception e) {
            logger.error(FlowableHelper.formatMessage("任务实例[{}]执行当前任务出现异常!", taskInstanceId), e);
            taskStatus = ProcessConstants.STATUS_EXCEPTION;
            errorMsg = e.getMessage();
            result = ObjectResult.createFailure(Constants.SYSTEM_ERROR_CODE, errorMsg);
        } finally {
            
            if (Objects.isNull(ProcessConfigContext.getContext())) {
                // Tasks.updateInsTaskStatus(nodeInstId, taskId, taskStatus, errorMsg);
            }
            
        }
    }
    
    public Result<?> getResult() {
        return result;
    }


    public static class TaskRunnerBuilder {
        private TaskParameter taskParameter;
        private ExecuteMode executeMode;
        private String runValue;
        private String taskInstanceId;

        TaskRunnerBuilder() {
        }

        public TaskRunnerBuilder taskParameter(TaskParameter taskParameter) {
            this.taskParameter = taskParameter;
            return this;
        }

        public TaskRunnerBuilder executeMode(ExecuteMode executeMode) {
            this.executeMode = executeMode;
            return this;
        }

        public TaskRunnerBuilder runValue(String runValue) {
            this.runValue = runValue;
            return this;
        }

        public TaskRunnerBuilder taskInstanceId(String taskInstanceId) {
            this.taskInstanceId = taskInstanceId;
            return this;
        }

        public TaskRunner build() {
            return new TaskRunner(taskInstanceId, taskParameter, executeMode, runValue);
        }

    }
}
