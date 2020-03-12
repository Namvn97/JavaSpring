package com.example.demo.com.example.demo;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFolderWin {

    private void scanFolder() {
        // TODO: file cần scan C:\Users\phannam\Downloads\DMKH\120\20200303_NGUYEN TUYEN_123456789\abc.docx and bcd.xlsx
        String pathTest = "C:\\Users\\phannam\\Downloads\\DMKH";
        File file = new File(pathTest);
        if (!file.exists()) {
            return;
        }
        String cusName;
        String idCard;
        List<String> folderLV1PathLst = scanFolder(pathTest);
        List<String> branchFolderLst = new ArrayList<>();
        List<String> cusProfileFolderLst = new ArrayList<>();
        List<String> checkAgainLst = new ArrayList<>();

        for (String folderPath : folderLV1PathLst) {
            String subFolder1Path = folderPath.replace(pathTest + "\\", "");
            if (StringUtils.trimToNull(subFolder1Path) != null) {
                String[] subFolder = subFolder1Path.split("\\\\");
                if (subFolder.length == 1) {
                    branchFolderLst.add(folderPath);
                }
            }
        }

        if (branchFolderLst.size() > 0) {
            for (String branch : branchFolderLst) {
                List<String> cusFolderLst = scanFolder(branch);
                for (String cusFolder : cusFolderLst) {
                    String subCusFolderPath = cusFolder.replace(branch + "\\", "");
                    String[] subFolder = subCusFolderPath.split("\\\\");
                    if (subFolder.length == 1) {
                        String[] elName = subFolder[0].split("_");
                        if (elName.length != 3) {
                            checkAgainLst.add(cusFolder);
                        } else {
                            //TODO: call service check get CIF
                            cusName = elName[1];
                            idCard = elName[2];

                            boolean callCore = true;
                            if (callCore) {
                                cusProfileFolderLst.add(cusFolder);
                            } else {
                                checkAgainLst.add(cusFolder);
                            }
                        }
                    } else {
                        checkAgainLst.add(cusFolder);
                    }
                }
            }
        }

        if (cusProfileFolderLst.size() > 0) {
            for (String cusFolder : cusProfileFolderLst) {
                List<String> files = scanFile(cusFolder);
                if (files.size() > 0) {
                    //TODO: Đẩy file vào

                }
            }
        }

        if (checkAgainLst.size() > 0) {
//			export(checkAgainLst);
        }

        System.out.println("==== END =====");
    }

    public List<String> scanFolder(String path) {
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {
            return walk.filter(Files::isDirectory).map(Path::toString).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> scanFile(String path) {
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {
            return walk.filter(Files::isRegularFile).map(Path::toString).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
