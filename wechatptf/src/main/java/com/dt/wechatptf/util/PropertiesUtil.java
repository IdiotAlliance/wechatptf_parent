package com.dt.wechatptf.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesUtil {

	private final Properties props;
	
	public PropertiesUtil(final Properties props){
		this.props = props;
	}
	
	public PropertiesUtil(final String fileloc){
		this.props = new Properties();
		final ClassLoader cl = PropertiesUtil.class.getClassLoader();
		final InputStream is = cl.getResourceAsStream(fileloc);
		if(is != null){
			try {
				this.props.load(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
    public String getStringProperty(final String name) {
        String prop = null;
        try {
            prop = System.getProperty(name);
        } catch (final SecurityException e) {
            // Ignore
        }
        return (prop == null) ? props.getProperty(name) : prop;
    }


    public int getIntegerProperty(final String name, final int defaultValue) {
        String prop = null;
        try {
            prop = System.getProperty(name);
        } catch (final SecurityException e) {
            // Ignore
        }
        if (prop == null) {
            prop = props.getProperty(name);
        }
        if (prop != null) {
            try {
                return Integer.parseInt(prop);
            } catch (final Exception ex) {
                return defaultValue;
            }
        }
        return defaultValue;
    }


    public long getLongProperty(final String name, final long defaultValue) {
        String prop = null;
        try {
            prop = System.getProperty(name);
        } catch (final SecurityException e) {
            // Ignore
        }
        if (prop == null) {
            prop = props.getProperty(name);
        }
        if (prop != null) {
            try {
                return Long.parseLong(prop);
            } catch (final Exception ex) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    public String getStringProperty(final String name, final String defaultValue) {
        final String prop = getStringProperty(name);
        return (prop == null) ? defaultValue : prop;
    }

    public boolean getBooleanProperty(final String name) {
        return getBooleanProperty(name, false);
    }

    public boolean getBooleanProperty(final String name, final boolean defaultValue) {
        final String prop = getStringProperty(name);
        return (prop == null) ? defaultValue : "true".equalsIgnoreCase(prop);
    }

}
