# htmlToPdf
Reporting in android studio
> Step 1. Add the JitPack repository to your build file
<br>Add it in your root setting.gradle(Project Setting) at dependencyResolutionManagement's repositories:
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
<br> Add it in your root build.gradle (Module) at the end of dependencies
```gradle
dependencies { 
	       implementation 'com.github.manojc362:htmlToPdf:1.0.1'
  
	}
  ```

## How to use Example on Activity 
  > crating the html template, key should be include with {}
  ```
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
