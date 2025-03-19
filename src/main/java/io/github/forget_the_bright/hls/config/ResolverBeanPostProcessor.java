package io.github.forget_the_bright.hls.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义 BeanPostProcessor 实现类，用于在 Spring 容器初始化完成后，
 * 对特定的 Bean（如 RequestMappingHandlerAdapter）进行后处理。
 * 主要功能是为 {@code RequestMappingHandlerAdapter} 添加自定义的参数解析器。
 */
@Slf4j
public class ResolverBeanPostProcessor implements BeanPostProcessor {

    /**
     * 在 Bean 初始化完成后执行的后处理方法。
     * 该方法会检查当前 Bean 是否为 {@code RequestMappingHandlerAdapter}，
     * 如果是，则为其添加自定义的参数解析器。
     *
     * @param bean     当前需要处理的 Bean 实例
     * @param beanName 当前 Bean 的名称
     * @return 处理后的 Bean 实例
     * @throws BeansException 如果在处理过程中发生异常
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.debug("-------------------------------" + beanName);
       // System.out.println("-------------------------------" + beanName);
        if (beanName.equals("requestMappingHandlerAdapter")) {
            // 对 requestMappingHandlerAdapter 进行修改
            RequestMappingHandlerAdapter adapter = (RequestMappingHandlerAdapter) bean;
            List<HandlerMethodArgumentResolver> argumentResolvers = adapter.getArgumentResolvers();

            // 添加自定义参数处理器
            argumentResolvers = addArgumentResolvers(argumentResolvers);

            adapter.setArgumentResolvers(argumentResolvers);
        }
        return bean;
    }

    /**
     * 添加自定义参数解析器到现有的参数解析器列表中。
     *
     * @param argumentResolvers 原始的参数解析器列表
     * @return 包含自定义参数解析器的新列表
     */
    private List<HandlerMethodArgumentResolver> addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        List<HandlerMethodArgumentResolver> resolvers = new ArrayList<>();

        //将自定的添加到最前面
        resolvers.add(new EnumParamArgumentResolver());
        //将原本的添加后面
        resolvers.addAll(argumentResolvers);
        return  resolvers;
    }
}
