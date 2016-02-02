/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.ml.vo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manuel
 */
public class CsvTableFactory {

    private List<String> objList = new ArrayList<String>();

    private final String voPackage = "pt.mleiria.sibs.ml.";
    
    

    public CsvTableFactory() {

        objList.add("HouseBusiness");

        objList.add("TruckBusiness");

    }

    public CsvTable getCsvTable(String obj) {
        if (null == obj) {
            return null;
        }
        for (String str : objList) {
            try {
                if (obj.equals(str)) {
                    Class<?> clazz = Class.forName(voPackage + str);
                    Constructor<?> ctor = clazz.getConstructor();
                    Object object = ctor.newInstance(new Object[]{});
                    return (CsvTable) object;
                }

            } catch (ClassNotFoundException e) {
             // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SecurityException e) {
             // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
             // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
             // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InstantiationException e) {
             // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
             // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
             // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

}
