package com.hdh.redpacket.game.task;

import com.hdh.redpacket.core.exception.SysException;
import com.hdh.redpacket.game.service.GameService;
import com.hdh.redpacket.system.constant.GameStatusTypeEnum;
import com.hdh.redpacket.system.model.TaskCron;
import com.hdh.redpacket.system.service.TaskCronService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

@Configuration
@EnableScheduling
public class StopInputTask implements SchedulingConfigurer {

    @Autowired
    private TaskCronService taskCronService;

    @Autowired
    private GameService gameService;

    /**
     * 执行定时任务.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> gameService.stopInputDiamond(),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    TaskCron taskCron = taskCronService.getByType(GameStatusTypeEnum.STOP_INPUT.getCode());
                    if(taskCron == null || StringUtils.isBlank(taskCron.getCron())){
                        throw SysException.SYS_ERROR;
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(taskCron.getCron()).nextExecutionTime(triggerContext);
                }
        );
    }

}
