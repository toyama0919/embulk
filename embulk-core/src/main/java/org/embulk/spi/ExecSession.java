package org.embulk.spi;

//import org.slf4j.Logger;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.embulk.config.Task;
import org.embulk.config.ModelManager;
import org.embulk.config.CommitReport;
import org.embulk.config.NextConfig;
import org.embulk.config.ConfigSource;
import org.embulk.config.TaskSource;
import org.embulk.config.DataSourceImpl;
import org.embulk.plugin.PluginType;
import org.embulk.plugin.PluginManager;
import org.embulk.jruby.JRubyBridge;

public class ExecSession
{
    //private final Logger logger;

    private final Injector injector;
    private final ModelManager modelManager;
    private final PluginManager pluginManager;
    private final BufferAllocator bufferAllocator;
    //private final NoticeLogger noticeLogger;
    private final JRubyBridge jrubyBridge;

    @Inject
    ExecSession(Injector injector /*, Logger logger, NoticeLogger noticeLogger*/)
    {
        super();
        this.injector = injector;
        //this.logger = logger;
        //this.noticeLogger = noticeLogger;
        this.modelManager = injector.getInstance(ModelManager.class);
        this.pluginManager = injector.getInstance(PluginManager.class);
        this.bufferAllocator = injector.getInstance(BufferAllocator.class);
        this.jrubyBridge = injector.getInstance(JRubyBridge.class);
    }

    //public Logger getLogger()
    //{
    //    return logger;
    //}

    //public NoticeLogger notice()
    //{
    //    return noticeLogger;
    //}

    public Injector getInjector()
    {
        return injector;
    }

    public BufferAllocator getBufferAllocator()
    {
        return bufferAllocator;
    }

    public <T> T newPlugin(Class<T> iface, PluginType type)
    {
        return pluginManager.newPlugin(iface, type);
    }

    public CommitReport newCommitReport()
    {
        return new DataSourceImpl(modelManager);
    }

    public NextConfig newNextConfig()
    {
        return new DataSourceImpl(modelManager);
    }

    public ConfigSource newConfigSource()
    {
        return new DataSourceImpl(modelManager);
    }

    public TaskSource newTaskSource()
    {
        return new DataSourceImpl(modelManager);
    }

    public JRubyBridge getJRubyBridge()
    {
        return jrubyBridge;
    }
}