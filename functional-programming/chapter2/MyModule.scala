object MyModule {

  def main(args: Array[String]): Unit = {
    println(formatAbs(-42)) 
    println(formatFactorial(7))
  }
  
  def formatResult(name: String, n: Int, f: Int => Int) = { 
    val msg = "The %s of %d is %d."
    msg.format(name, n, f(n))
  }

  private def formatAbs(x: Int) = {
    val msg = "The absolute value of %d is %d." 
    msg.format(x, abs(x))
  }
  
  private def formatFactorial(n: Int) = { 
    val msg = "The factorial of %d is %d." 
    msg.format(n, factorial(n))
  }

  def abs(n: Int): Int = {
    if ( n < 0 ) -n
    else n
  }

  def factorial(n: Int) = {
    def go(n: Int, previousVal: Int): Int = {
      if ( n <= 0) previousVal
      else go(n-1, previousVal*n )
    }

    go(n, 1)
  }

  def fib(n: Int): Int = {
    def go(i: Int, j: Int, k: Int): Int = {
      if (n == 0) 0
      else if (n == 1 || n == 2) 1
      else if ( i < n ) go(i+1, k, j+k)
      else j + k
    }
    go(1, 0, 1)
  }

}