package soho.chloe.informationmanager.web;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import soho.chloe.informationmanager.utils.InformationManagerConstants;

public class ChloePropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {
	private static Map<String, Object> ctxPropertiesMap = new HashMap<String, Object>();

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			ctxPropertiesMap.put(keyStr, value);
		}

		checkUploadDir();
	}

	private void checkUploadDir() {
		File testImage = new File(
				(String) ChloePropertyPlaceholderConfigurer
						.getContextProperty(InformationManagerConstants.HOUSE_IMAGE_DIR));
		if (!testImage.exists()) {
			testImage.mkdirs();
		}
		File testImageThumbnail = new File(
				(String) ChloePropertyPlaceholderConfigurer
						.getContextProperty(InformationManagerConstants.HOUSE_IMAGE_THUMBNAIL_DIR));
		if (!testImageThumbnail.exists()) {
			testImageThumbnail.mkdirs();
		}
	}

	public static Object getContextProperty(String name) {
		return ctxPropertiesMap.get(name);
	}
}
