package com.facerun.service.university;

import com.facerun.bean.University;
import com.facerun.bean.UniversityExample;
import com.facerun.dao.CustRegionMapper;
import com.facerun.dao.CustUniversityMapper;
import com.facerun.dao.RegionMapper;
import com.facerun.dao.UniversityMapper;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xinzhendi-031 on 2017/12/1.
 */
@Service
@Transactional
public class UniversityService {
    private static final Logger log = LoggerFactory.getLogger(UniversityService.class);

    @Autowired
    private RegionMapper regionMapper;

    @Autowired
    private UniversityMapper universityMapper;

    @Autowired
    private CustRegionMapper custRegionMapper;

    @Autowired
    private CustUniversityMapper custUniversityMapper;

    private String xlsPath = "D:\\university2.xlsx";
    private String sheetName = "sheet1";

    /**
     * 构造函数，初始化excel数据
     * @param filePath  excel路径
     * @param sheetName sheet表名
     */
    public XSSFSheet getXSSFSheet(String filePath,String sheetName){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
            //获取sheet
            XSSFSheet sheet = sheets.getSheet(sheetName);
            return sheet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //打印excel数据
    public void readExcelData(){
        XSSFSheet sheet = getXSSFSheet(xlsPath,sheetName);
        if (sheet == null)
            return;
        //获取行数
        int rows = sheet.getPhysicalNumberOfRows();
        for(int i=2;i<rows;i++){
            //获取列数
            XSSFRow row = sheet.getRow(i);
            int columns = row.getPhysicalNumberOfCells();
            University university = new University();
            for(int j=1;j<columns;j++){
                String cell = row.getCell(j).toString();
                if (StringUtils.isEmpty(cell)){
                    break;
                }
//                System.out.println(cell);
                switch (j){
                    case 1:
                        university.setName(cell);
                        break;
                    case 2:
                        university.setCode(cell);
                        break;
                    case 3:
                        university.setDepartment(cell);
                        break;
                    case 4:
                        university.setLocation(cell);
                        break;
                    case 5:
                        university.setDegree(cell);
                        break;
                    case 6:
                        university.setNote(cell);
                        break;
                }
            }
            if (!StringUtils.isEmpty(university.getName()))
                universityMapper.insert(university);
        }
    }

    public List<University> queryUniversities(Map params){
        List<University> list = universityMapper.selectByExample(null);
        return list;
    }

    public Object queryUniversitiesC(Map params){
        Map paramsP = new HashMap();
        List<Map> provinces = custRegionMapper.queryRegionP(paramsP);
        for (Map province : provinces){
            int id = (int) province.get("id");
            Map paramsPC = new HashMap();
            paramsPC.put("pid",id);
            List<Map> citys = custRegionMapper.queryRegionPC(paramsPC);
            province.put("city",citys);
            for (Map city : citys){
                String name = (String) city.get("name");
                Map paramsU = new HashMap();
                paramsU.put("city",name);
                List<Map> list = custUniversityMapper.queryUniversitiesC(paramsU);
                city.put("university",list);
            }
        }
        return provinces;
    }

    public List<University> queryUniversityByName(Map params){
        String name = MapUtils.getString(params, "name");
        UniversityExample example = null;
        if (!StringUtils.isEmpty(name)) {
            String lName = "%"+name+"%";
            example = new UniversityExample();
            example.createCriteria().andNameLike(lName);
        }
        List<University> list = universityMapper.selectByExample(example);
        return list;
    }

    public List<University> queryUniversityByCity(Map params){
        String city = MapUtils.getString(params, "city");
        UniversityExample example = null;
        if (!StringUtils.isEmpty(city)) {
            String lName = "%"+city+"%";
            example = new UniversityExample();
            example.createCriteria().andLocationLike(lName);
        }
        List<University> list = universityMapper.selectByExample(example);
        return list;
    }

}
