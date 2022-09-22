package com.mengjq.assignmentsubmission.core;
import com.mengjq.assignmentsubmission.conf.LanguageSet;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;

public class EchoCLI {
    public void colorPrint(String str, String color) {
        // turn color to the cli color code like \033[0m
        switch (color) {
            case "red":
                color = "\033[31m";
                break;
            case "green":
                color = "\033[32m";
                break;
            case "yellow":
                color = "\033[33m";
                break;
            case "blue":
                color = "\033[34m";
                break;
            case "purple":
                color = "\033[35m";
                break;
            case "cyan":
                color = "\033[36m";
                break;
            case "white":
                color = "\033[37m";
                break;
            default:
                color = "\033[0m";
                break;
        }
        System.out.println(color + str + "\033[0m");
    }

    public void showAssignments(FindIterable<Document> assignments){
        if (!assignments.cursor().hasNext()) {
            System.out.println("No assignments");
            return;
        }
        System.out.println("___________________________");
        String[] headers = {"assiId", "DDL", "briefName", "describe",  "status"};
        System.out.printf("%-10s %-20s %-20s %-20s %-10s\n", headers);
        System.out.println("￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
        for (Document assignment : assignments) {
            // print the value of headers with equal length
            System.out.printf("%-10s %-20s %-20s %-20s %-10s\n",
                    assignment.getString("assiId"),
                    assignment.getString("briefName"),
                    assignment.getString("describe"),
//                    assignment.getString("fileNameRule"),
                    assignment.getString("DDL"),
                    assignment.getString("status"));
        }
        System.out.println("--------------------------");
    }

    public void showMySubmitStatus(FindIterable<Document> docs){
        System.out.println("studentId: ");
        if (!docs.cursor().hasNext()) {
            System.out.println("No submissions");
            return;
        }
        // acquire the number of docs
        int num = 0;
        for (Document doc : docs) {
            num++;
        }

        String[] fields = {"status", "uploadTime", "fileSize", "formatName"};
        // print the values of fields with equal length
        System.out.println("--------------------------");
        System.out.printf("%-10s %-10s %-10s %-10s\n", fields);
        System.out.println("--------------------------");
        for (Document doc : docs) {
            Object fileSize = doc.get("fileSize");
            double fileSizeMB;
            if (fileSize == null) {
                fileSizeMB = 0;
            }else {
                // convert the String filesize to String fileSize with MB format
                fileSizeMB = Double.parseDouble(String.valueOf((Integer) fileSize)) / 1024 / 1024;
                // keep fileSizeMB with 2 decimal places
                fileSizeMB = Math.round(fileSizeMB * 100) / 100.0;
            }
            System.out.printf("%-10s %-10s %-10s %-10s\n",
//                    doc.getString("stuId"),
//                    doc.getString("stuName"),
                    doc.getString("status"),
                    doc.getString("uploadTime"),
                    Double.toString(fileSizeMB) + "MB",
                    doc.getString("formatName"));
        }
        System.out.println("--------------------------");
        System.out.println("Total: " + num + " submissions");
    }

    public void showAllSubmitStatus(FindIterable<Document> assignments, FindIterable<Document> fileInfos, FindIterable<Document> stuInfos, LanguageSet languageSet){
        // 人名  A作业  B作业  C作业
        // 孟    未交   已交   已交
        // 张    未交   未交   已交

        // Headers for the table
        if (!assignments.cursor().hasNext()) {
            System.out.println(languageSet.echoCLIShowAllStatusNoAssign);
        }else {
            // show the briefName of assignments as header with equal length
            System.out.println("--------------------------");
            for (Document assignment : assignments) {
                System.out.printf("%-10s\t", assignment.getString("briefName"));
            }
            System.out.println("\n--------------------------");
        }

        // show all of fileInfos
        if (!fileInfos.cursor().hasNext()) {
            System.out.println(languageSet.echoCLIShowAllStatusNoFile);
            return;
        }

        // There are stuInfos and fileInfos
        if (!stuInfos.cursor().hasNext()) {
            System.out.println(languageSet.echoCLIShowAllStatusNoStu);
            showAssignments(assignments);
        } else {

            // Type 1: has Correct stuId
            for (Document stuInfo : stuInfos) {
                FindIterable<Document> filesOfStuId = fileInfos.filter(new Document("stuId", stuInfo.getString("stuId")));
                for (Document fileInfo : filesOfStuId) {
                    System.out.println(fileInfo.getString("stuName") + " " + fileInfo.getString("stuId"));
                    System.out.printf("\t%-10s", fileInfo.getString("uploadTime"));
                    System.out.printf("\t%-10s", fileInfo.getString("formatName"));
                    // format the fileSize and keep two decimal s
                    System.out.printf("\t%-10s", Math.round(Double.parseDouble(String.valueOf(fileInfo.getInteger("fileSize"))) / 1024 / 1024 * 100) / 100.0 + "MB");
                }


                System.out.println();
            }
            // Type 2: query data has no stuId
            System.out.print("-------------------------------------👇");
            System.out.print(languageSet.echoCLIShowAllStatusUnknown);
            System.out.println("👇-------------------------------------");
            FindIterable<Document> filesOfstuId = fileInfos.filter(eq("stuId", null));
            for (Document fileInfo : filesOfstuId) {
                System.out.println(fileInfo.getString("stuName") + ":");
                System.out.printf("\t%-10s", fileInfo.getString("uploadTime"));
                System.out.printf("\t%-10s", fileInfo.getString("rawName"));
            }
            System.out.print("\n-------------------------------------👆");
            System.out.print(languageSet.echoCLIShowAllStatusUnknown);
            System.out.println("👆-------------------------------------");
            // Ver1: status
            //孟骏清
            //	2022-7-8 10:29:13	孟骏清-19852331.xlsx孟骏清
            //	2022-7-18 10:29:17	孟骏清-19852331.zip孟骏清
            //	2022-7-8 10:44:33	孟骏清-数据结构测试
            //赵云龙
            //	2022-7-28 12:29:24	赵云龙-19852331.docx
            //-----------no status---------------
            //null
            //	2022-07-09 14:31:30	readme.md null
            //	2022-07-09 19:18:32	readme.md
            //-----------no status---------------

//            List<Document> otherFiles = new ArrayList<>();
//            // iterable fileInfos
////            for (Document fileInfo : fileInfos) {
////                // iterable stuInfos
//                for (Document stuInfo : stuInfos) {
//                    // iterable the assiId value of assignments
//                    for (Document assignment : assignments) {
//                        fileInfos.filter(new Document("assiId", assignment.getString("assiId")));
//
//                    }
//                        if (fileInfo.getString("stuId").equals(stuInfo.getString("stuId"))) {
//                            // print the stuInfo
//                            System.out.printf("%-10s\t", fileInfo.getString("status"));
//                        }
//
//
//
//                    // Type 1 : belong to the designed student
//                    try {
//                        fileInfo.getString("stuId");
//                        // if the stuId of fileInfo equals to stuId of stuInfo, then print the status
//
//                    } catch (Exception e) {
////                        e.printStackTrace();
//                        System.out.printf("%-10s\t", fileInfo.getInteger("rawName"));
//                    }
//
//                    // Type 2 : not belong to the designed student
//                    otherFiles.add(fileInfo);
//
//                    System.out.println(stuInfo.get("stuName"));
//                    System.out.println('\n');
//                }

        }


//        Aggregation agg = Aggregation.newAggregation(
//                Aggregation.match(new Document("status", "submitted")),
//                Aggregation.group("stuId").count().as("count")
//        );

        // show all files from mongoDB

        // show other files

//        String[] fields = {"assiId", "stuId", "stuName", "uploadTime", "fileSize", "formatName", "status"};
//        System.out.println("--------------------------");
//        for (String field : fields) {
//            System.out.print(field);
//            System.out.print("\t");
//        }
//        System.out.println();
//        System.out.println("--------------------------");
//        for (Document doc : assignments) {
//            for (String field : fields) {
//                System.out.print(doc.get(field));
//                System.out.print("\t");
//            }
//            System.out.println();
//        }
//        System.out.println("--------------------------");
    }

    public void echo(String s) {
        System.out.println(s);
    }

    public void echoInline(String s){
        System.out.print(s);
    }


    public void showStuInfo(Document stuInfo) {
        System.out.print("Current student: ");
        if (stuInfo == null) {
            colorPrint("No student selected", "red");
        } else {
            colorPrint("" + stuInfo.getString("stuId") +
                    "\t" + stuInfo.getString("stuName")  +
                    "\t" + stuInfo.getString("clazz"), "green");
        }
    }


    public void noStuInfo(LanguageSet languageSet) {
        System.out.println(languageSet.setStuInfoFirst);
    }

    public void fileUpOver(LanguageSet languageSet) {
        System.out.println(languageSet.sendUploadOver);
    }

    public void getMenuAbout(LanguageSet languageSet){
        System.out.println(languageSet.menuAbout);
    }

}
