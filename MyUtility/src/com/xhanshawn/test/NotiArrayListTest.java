package com.xhanshawn.test;

import static org.junit.Assert.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.xhanshawn.myutility.NotiArrayList;
import com.xhanshawn.myutility.NotiArrayList.OnSizeChangeListener;
import com.xhanshawn.myutility.NotiArrayList.SizeChangeEvent;

public class NotiArrayListTest {

	int old1 = 0;
	int v11 = 0;

//	@Test
//	public void test() {
//		NotiArrayList<Integer> list = new NotiArrayList<Integer>();
//		int old = 0;
//		int v1 = 1;
//		int v2 = 4;
//		list.addChangeListener(new PropertyChangeListener(){
//
//			@Override
//			public void propertyChange(PropertyChangeEvent evt) {
//				// TODO Auto-generated method stub
//				System.out.println(evt.getPropertyName());
//				old1= (int) evt.getOldValue();
//				v11 = (int) evt.getNewValue();
//			}
//		});
//		list.add(1);
//		assertEquals(v1, v11);
//		assertEquals(old, old1);
//
//		List<Integer> list2 = new ArrayList<Integer>();
//		list2.add(2);
//		list2.add(3);
//		list2.add(4);
//		list.addAll(list2);
//		assertEquals(v2, v11);
//		assertEquals(old1, 1);
//
//	}
	
	
	@Test
	public void test2() {
		NotiArrayList<Integer> list = new NotiArrayList<Integer>();
		int old = 0;
		int v1 = 1;
		int v2 = 4;
		list.setOnSizeChangeListener(new OnSizeChangeListener(){

			@Override
			public void sizeChange(SizeChangeEvent event) {
				// TODO Auto-generated method stub
				if(event.getEvent() == SizeChangeEvent.ADD) {
					v11 = event.getNewSize();
					old1 = event.getOldSize();
				}
			}
			
		});
		list.add(1);
		assertEquals(v1, v11);
		assertEquals(old, old1);

		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(2);
		list2.add(3);
		list2.add(4);
		list.addAll(list2);
		assertEquals(v2, v11);
		assertEquals(old1, 1);

	}

}
