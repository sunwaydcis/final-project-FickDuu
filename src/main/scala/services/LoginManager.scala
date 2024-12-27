package services

object LoginManager{
  private var loggedInUser: Option[String] = None

  def login(username: String): Boolean = {
    if(username.trim.isEmpty){
      println("Username cannot be empty")
      false
    }
    else{
      loggedInUser = Some(username)
      println(s"User '$username' logged in.")
      true
    }
  }

  def logout(): Unit ={
    loggedInUser = None
    println("Logged Out")
  }

  def currentUser: Option[String] = loggedInUser
}