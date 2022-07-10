Android Library to Generate PDF.
## Table of contents
* [Introduction](#introduction)
* [How to use with HashMap](#how-to-use-with-hashmap)
* [How to use with List](#how-to-use-with-list)
* [Setup](#setup)
## Introduction
This project is simply generate pdf from HTML codes.
	
## How to use with HashMap
  > crating the html template, key should be include with {}
  ```java
   String htmlTemplate="<h1>Course : {course} , age :{age}</h1>";
  ```
  > Creating  keyValuePair to be replace
  ```
   HashMap<String,String> keyValuePairHashMap = new HashMap<>();
   keyValuePairHashMap.put("course","CSE11");
   keyValuePairHashMap.put("age","900");
  ```
  > Generate pdf with file name 'Report1' 
  ```
   GeneratePdf generatePdf = new GeneratePdf(MainActivity.this, "Report1",htmlTemplate,keyValuePairHashMap);
  ```
## How to use with List
  > crating the html template, key should be include with {}
  ```java
   String htmlTemplate="<h1>Course : {course} , age :{age}</h1>";
  ```
  > Creating  key list and value list to be replace
  ```
   List<String> keyList = new ArrayList<String>();
   keyList.add("course");
   keyList.add("age");
   List<String> valueList = new ArrayList<String>();
   valueList.add("CSE-GPDURG");
   valueList.add("25");
  ```
  > Generate pdf with file name 'ReportKeyValue1' 
  ```
   GeneratePdf generatePdf = new GeneratePdf(MainActivity.this, "ReportKeyValue1",htmlTemplate,keyList,valueList);
  ```
## Setup
> Step 1. Add the JitPack repository to your build file
<br>Add it in your root <b>setting.gradle(Project Setting)</b> at dependencyResolutionManagement's repositories:
```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        maven { url 'https://jitpack.io' }
        mavenCentral()
    }
}
```
> Step 2. Add the dependency
<br> Add it in your root <b>build.gradle (Module)</b> at the end of dependencies
```gradle
dependencies { 
	       implementation 'com.github.manojc362:htmlToPdf:1.0.1'
	}
 ```
[![](https://jitpack.io/v/manojc362/htmlToPdf.svg)](https://jitpack.io/#manojc362/htmlToPdf)
