//class Bag[K, V] () {
//  var content: Map[K, Set[V]] = Map()
//
//  def add(k: K) = {
//    if (content contains k) {
//      content += (k -> (content(k) + k))
//    } else {
//      content += (k -> Set(v))
//    }
//  }
//
//  def asCounts: Map[K, Int] = {
//    content.map(e => e._1 -> e._2.size)
//  }
//}