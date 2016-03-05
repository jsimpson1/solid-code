object MyModule {

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
    def go(nn: Int, prev: Int): Int = {
      if (nn < n) go(nn+1, prev + (nn+1) )
      else prev
    }
    go(0, 0)
  }

}