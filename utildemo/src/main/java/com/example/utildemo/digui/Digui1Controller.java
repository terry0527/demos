package com.example.utildemo.digui;

import com.example.utildemo.entity.DeptTreeDTO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Digui1Controller {

    public static List<DeptTreeDTO> listWithTree(List<DeptTreeDTO> depts) {
        //父级
        List<DeptTreeDTO> parents = depts.stream().map((item) -> {
            DeptTreeDTO dept = new DeptTreeDTO();
            dept.setName(item.getPname());
            dept.setSort(item.getSort());
            dept.setPname("");
            return dept;
        }).collect(Collectors.toList());

        parents = parents.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(o -> o.getName()))),
                        ArrayList::new));

        return parents.stream()
                //.filter(categoryEntity -> categoryEntity.getPName() != null)
                .peek(menu -> menu.setChildren(getChildless(menu, depts)))
                .sorted(Comparator.comparing(DeptTreeDTO::getSort).reversed())
                .collect(Collectors.toList());
    }

    /**
     * 递归查找所有菜单的子菜单
     */
    private static List<DeptTreeDTO> getChildless(DeptTreeDTO root, List<DeptTreeDTO> all) {
        return all.stream()
                .filter(categoryEntity -> categoryEntity.getPname().equals(root.getName()))
                .peek(categoryEntity -> {
                    // 找到子菜单
                    if (categoryEntity.getPname().equals("")) {
                        categoryEntity.setChildren(getChildless(categoryEntity, all));
                    }
                })
                //.sorted(Comparator.comparing(DeptTreeDTO::getSort).reversed())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        List<DeptTreeDTO> list1 = new ArrayList<>();
        DeptTreeDTO dto = new DeptTreeDTO();
        dto.setName("呼吸内科");
        dto.setSort(1);
        dto.setPname("内科");

        DeptTreeDTO dto2 = new DeptTreeDTO();
        dto2.setName("消化内科");
        dto2.setSort(1);
        dto2.setPname("内科");

        DeptTreeDTO dto3 = new DeptTreeDTO();
        dto3.setName("儿胸外科");
        dto3.setSort(2);
        dto3.setPname("儿科");

        DeptTreeDTO dto4 = new DeptTreeDTO();
        dto3.setName("儿外科");
        dto4.setSort(3);
        dto4.setPname("儿科");

        list1.add(dto);
        list1.add(dto2);
        list1.add(dto3);
        list1.add(dto4);

        List<DeptTreeDTO> showlist= listWithTree(list1);
        showlist.stream().forEach(student -> {
            System.out.println("-------------------"+student.getName());
        });

    }
}
