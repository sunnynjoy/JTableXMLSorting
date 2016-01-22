package com.joy.jT.utils;

import java.io.File;

import javax.inject.Singleton;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.joy.jT.exception.ServiceException;
import com.joy.jT.model.Data;
import com.joy.jT.model.Layout;

@Singleton
public class ParseUtil {

	public static Object unmarshall(String path, Instance instance) throws ServiceException {
		JAXBContext jaxbContext = null;
		Unmarshaller jaxbUnmarshaller = null;
		try {
			File file = new File(path);
			if (Instance.LAYOUT.equals(instance)) {
				jaxbContext = JAXBContext.newInstance(Layout.class);
				jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				return (Layout) jaxbUnmarshaller.unmarshal(file);
			} else {
				jaxbContext = JAXBContext.newInstance(Data.class);
				jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				return (Data) jaxbUnmarshaller.unmarshal(file);
			}
		} catch (JAXBException e) {
			throw new ServiceException("Error occured while Marshalling or Unmarshalling");
		}
	}
}
