import java.io.File
import java.nio.file.Files
import scala.collection.immutable.ListMap
import scala.collection.mutable.ListBuffer
import scala.io.Source
import java.nio.file.StandardCopyOption
import java.nio.file.Paths

object Main extends App {
  /* TODO
      * Read all notes files ending with .MD [x]
      * Iterate through all notes counting the words adding them to a Bag
      * Filter out common words articles, prepositions, conjunctions, etc.
      * Create local copy of notes in a new directory
      * Replace all words in notes with their frequency in the Bag when over a certain frequency
  */

  val popularWords = Set("the","of","to","and","a","in","is","it","you","that","he","was","for","on","are","with","as","I","his","they","be","at","one","have","this","from","or","had","by","hot","word","but","what","some","we","can","out","other","were","all","there","when","up","use","your","how","said","an","each","she","which","do","their","if","will","way","about","many","then","them","write","would","like","so","these","her","long","make","thing","see","him","two","has","look","more","day","could","go","come","did","number","sound","no","most","people","my","over","know","than","call","first","who","may","down","side","been","now","find","any","new","work","part","take","get","place","made","live","where","after","back","little","only","round","man","year","came","show","every","good","me","give","our","under","name","very","through","just","form","sentence","great","think","say","help","low","line","differ","turn","cause","much","mean","before","move","right","boy","old","too","same","tell","does","set","three","want","air","well","also","play","small","end","put","home","read","hand","port","large","spell","add","even","land","here","must","big","high","such","follow","act","why","ask","men","change","went","light","kind","off","need","house","picture","try","us","again","point","near","build","self","head","stand","own","page","should","found","grow","study","still","plant","cover","food","four","between","state","keep","eye","never","last","let","thought","city","tree","cross","farm","hard","start","might","story","saw","far","sea","draw","left","late","run","don't","while","press","close","night","real","few","north","open","seem","together","next","white","begin","got","walk","example","ease","paper","group","always","music","those","both","mark","often","letter","until","mile","river","car","feet","care","second","book","carry","took","science","eat","room","friend","began","idea","fish","mountain","stop","once","base","hear","horse","cut","sure","watch","color","face","wood","main","enough","plain","girl","usual","young","ready","above","ever","red","list","though","feel","talk","bird","soon","body","dog","direct","pose","leave","song","measure","door","product","black","short","numeral","wind","question","happen","complete","ship","area","half","rock","order","fire","south","piece","told","knew","pass","since","top","whole"
    //My common words
  , "something","cannot","rather","because","means","simply","create","subject","perhaps","essentially","everything","however","having","certain","different","understand","necessary","relation","using","interesting","known","against","fundamentally","believed","fundamental","useful","necessarily","likely","myself","regarding","table","itself","created","later","least","given","possible","according","within","although","condition","course","claim","greater","ultimate","avoid","level","extremely","during","almost","called","saying");

  val notesDir = "/home/greg/notes/Learning"


  def recursiveListFiles(f: File): Array[File] = {
    val these = f.listFiles
    these ++ these.filter(_.isDirectory).flatMap(recursiveListFiles)
  }

  def readFileContent(file: File) = Source.fromFile(file)

  val allNotesContent = recursiveListFiles(new File(notesDir))
    .filter(_.getName.toLowerCase.endsWith(".md"))
    .map(file => file.getPath -> readFileContent(file).mkString)
    .toMap

  val words = new ListBuffer[String]()

  allNotesContent.filter(_._2.nonEmpty) foreach { case (path, content) =>
    content.replaceAll("\\[\\[.*\\]\\]", "").split("[\\s+,.]").foreach(word => if (!word.contains("[[")) words += word.toLowerCase.replaceAll("[,.\\[\\]]", ""))
  }
  val qualifyingWords = ListMap(words.groupBy(identity)
    .map(e => e._1 -> e._2.size)
    .filter(_._1.length >= 5)
    .filter(_._2 >= 15)
    .filter(!_._1.endsWith("s"))
    .filter(e => !popularWords.contains(e._1))
    .toSeq.sortBy(_._2).reverse: _*).keys.toList

  Files.copy(Paths.get(notesDir), Paths.get("/tmp/notes-clone"), StandardCopyOption.REPLACE_EXISTING)

  System.out.println()
}
