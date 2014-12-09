/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coded.others;

import java.util.ArrayList;

/**
 *
 * @author Shehan
 */
public class Pool extends DirectProjectStaff {

    ArrayList list = new ArrayList();

    public boolean addEmployee(Employee ele) {
        list.add(ele);
        return true;
    }
}
