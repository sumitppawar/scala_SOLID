
  
SOLID Principles 
-
 As a software guy everyone should know the basic design principle.    
This is my try to explain ***SOLID principles*** using scala (FP way).  
  
Every principle is explained in three parts as below   
  
 1. What  (Simple definition)  
 2. Ways to achieve principles   
 3. Why   
 4. Example  
   1. Not following principle example   
   2. Principle followed example  
 5. Notes  
   
 Detail code example are given in under  dir `/src/com.sumit.learn.SOLID/`  
  
Lets start with full form of ***SOLID***   
 - [**S**ingle Responsibility Principle](#Single-Responsibility-Principle)  
 -  **O**pen Closed Principle  
 - **L**iskov Substitution Principle  
 - **I**nterface Segregation Principle  
 - **D**ependancy Inversion Principle  
  
    
  
#### Single Responsibility Principle  
  **What ?**  
  One software entity(Class, method, interface etc.) must do one thing.    
**Ways to achieve it ?**  
Segregate responsibility among related entity, rather that putting  
multiple things in a one entity.    
**Why ?**    
- Modular code  
 - Easy to test  
 - Change in one reason will not affect other entity, by this we only concern  
    testing about the changed entity.  
  
**Example**  
Bad example   
Lets consider we have printer class that prints given image.  

    case class Image(data: Array[Byte])
    class BadPrinter {
	    def printImageFromPath(path: String): Unit = {  
		    val image = Image(Files.readAllBytes(Paths.get(path)))  
			printImage(image)
		}
		def printImageFromURL(ulr: URL): Unit = {  
		    val image = Image from URL
			printImage(image)
		}
		private def printImage(image: Image) =
			println(s"Printing image... $image")
    }   
    
   In above example  
   ```BadPrinter```  is doing multiple things   
   1. It is doing IO to get image from Path/Url
   2. Printer should only do printing not IO    
   
**Good Example**  

    case class Image(data: Array[Byte])
    class GoodPrinter {
	    def printImage(image: Image) =
		    println(s"Printing image... $image")
	}
In above example Printer is doing only printing thing not IO.  
It has only one responsibility of printing.