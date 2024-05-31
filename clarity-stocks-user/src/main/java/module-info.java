module clarity.stocks.user {

  requires com.google.gson;
  exports user.model;
  opens user.model to com.google.gson;
}