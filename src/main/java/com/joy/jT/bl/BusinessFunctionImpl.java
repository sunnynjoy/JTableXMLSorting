package com.joy.jT.bl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Singleton;
import com.joy.jT.model.Car;

@Singleton
public class BusinessFunctionImpl implements BusinessFunction,Comparator<Car> {

	@Override
	public List<Car> doAction(List<Car> data) {
		Collections.sort(data,new BusinessFunctionImpl());
		return data;
	}

	@Override
	public int compare(Car car1, Car car2) {
		return car1.getMake().compareTo(car2.getMake());
	}

	
}
