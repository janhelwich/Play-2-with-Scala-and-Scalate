readAllPosts =  ->
  $("#blogarea").empty()
  $.get "/listposts", (data) ->
    $.each data, (index, post) ->
      $("#blogarea").append (
          "<div class=\"row well\">" +
            "<h2>" + post.title + "</h2>" +
            "<textarea readonly=\"readonly\" class=\"input-block-level\">" + post.content + "</textarea>" +
          "</div>"
      )

$(document).ready ->
  readAllPosts.apply()

  $('#button').click ->
    dataString = $("#blog-form").serialize()
    $.ajax(
      type: 'POST'
      url: '/submit-new-blog'
      data: dataString
      success: ->
        $("#reset").click()
        readAllPosts.apply()
    )
