import org.scalatest.FunSuite

case class Foo() {

}

class UserTests extends FunSuite {
  test("a user should exist") {
    val f = Foo()

    assert(f != null)
  }
}
