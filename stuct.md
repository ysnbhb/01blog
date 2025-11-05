```
├── README.md
├── backend
    ├── .gitattributes
    ├── .gitignore
    ├── .mvn
    │   └── wrapper
    │   │   └── maven-wrapper.properties
    ├── Makefile
    ├── mvnw
    ├── mvnw.cmd
    ├── pom.xml
    ├── src
    │   ├── main
    │   │   ├── java
    │   │   │   └── _blog
    │   │   │   │   └── com
    │   │   │   │       └── _blog
    │   │   │   │           ├── Application.java
    │   │   │   │           ├── Entity
    │   │   │   │               ├── Comment.java
    │   │   │   │               ├── Connection.java
    │   │   │   │               ├── Image.java
    │   │   │   │               ├── Notifacation.java
    │   │   │   │               ├── Post.java
    │   │   │   │               ├── Reaction.java
    │   │   │   │               ├── Report.java
    │   │   │   │               └── UserEntity.java
    │   │   │   │           ├── Exception
    │   │   │   │               ├── GlobalExceptionHandler.java
    │   │   │   │               └── ProgramExeption.java
    │   │   │   │           ├── controllers
    │   │   │   │               ├── AdminControl.java
    │   │   │   │               ├── Comments.java
    │   │   │   │               ├── ConnectionControl.java
    │   │   │   │               ├── NotifacationContral.java
    │   │   │   │               ├── PostController.java
    │   │   │   │               ├── ReactionContraler.java
    │   │   │   │               ├── ReportControl.java
    │   │   │   │               └── UserController.java
    │   │   │   │           ├── dto
    │   │   │   │               ├── CommentConert.java
    │   │   │   │               ├── ImageCovert.java
    │   │   │   │               ├── PostConvert.java
    │   │   │   │               ├── ReportConvet.java
    │   │   │   │               └── UserConvert.java
    │   │   │   │           ├── middleware
    │   │   │   │               ├── CorsConfig.java
    │   │   │   │               ├── JwtAuthFilter.java
    │   │   │   │               └── SecurityConfig.java
    │   │   │   │           ├── repositories
    │   │   │   │               ├── CommentsRepositories.java
    │   │   │   │               ├── ConnectionRepo.java
    │   │   │   │               ├── ImageRepo.java
    │   │   │   │               ├── NotifacationRepo.java
    │   │   │   │               ├── PostRepositery.java
    │   │   │   │               ├── ReactionRepo.java
    │   │   │   │               ├── ReportRepostiry.java
    │   │   │   │               └── UserRepository.java
    │   │   │   │           ├── services
    │   │   │   │               ├── AdminService.java
    │   │   │   │               ├── CommentsServ.java
    │   │   │   │               ├── ConnectionSrv.java
    │   │   │   │               ├── JwtService.java
    │   │   │   │               ├── NotifacationSer.java
    │   │   │   │               ├── PostServ.java
    │   │   │   │               ├── ReactionServer.java
    │   │   │   │               ├── ReportSer.java
    │   │   │   │               └── UserServ.java
    │   │   │   │           └── utils
    │   │   │   │               ├── AdminInitializer.java
    │   │   │   │               ├── CommentReq.java
    │   │   │   │               ├── CommentRes.java
    │   │   │   │               ├── ImageReq.java
    │   │   │   │               ├── NameValid.java
    │   │   │   │               ├── NotificationRes.java
    │   │   │   │               ├── PostReq.java
    │   │   │   │               ├── ReportReq.java
    │   │   │   │               ├── Upload.java
    │   │   │   │               ├── UserName.java
    │   │   │   │               ├── UserReq.java
    │   │   │   │               └── ValidDateOfBirth.java
    │   │   └── resources
    │   │   │   └── application.properties
    │   └── test
    │   │   └── java
    │   │       └── _blog
    │   │           └── com
    │   │               └── _blog
    │   │                   └── ApplicationTests.java
    └── uploads
    │   ├── images
    │       └── default-avatar.jpg
    │   └── video
    │       └── fe1e5a45-fd1f-463d-8032-eeb955e86083.mp4
└── frontend
    ├── .editorconfig
    ├── .gitignore
    ├── .vscode
        ├── extensions.json
        ├── launch.json
        └── tasks.json
    ├── README.md
    ├── angular.json
    ├── package-lock.json
    ├── package.json
    ├── public
        └── favicon.ico
    ├── src
        ├── app
        │   ├── all-report-user
        │   │   ├── all-report-user.css
        │   │   ├── all-report-user.html
        │   │   ├── all-report-user.spec.ts
        │   │   └── all-report-user.ts
        │   ├── app.config.ts
        │   ├── app.css
        │   ├── app.routes.ts
        │   ├── app.spec.ts
        │   ├── app.ts
        │   ├── comments
        │   │   ├── comments.css
        │   │   ├── comments.html
        │   │   ├── comments.spec.ts
        │   │   └── comments.ts
        │   ├── components
        │   │   ├── ban-popup
        │   │   │   ├── ban-popup.css
        │   │   │   ├── ban-popup.html
        │   │   │   ├── ban-popup.spec.ts
        │   │   │   └── ban-popup.ts
        │   │   ├── comment-view
        │   │   │   ├── comment-view.css
        │   │   │   ├── comment-view.html
        │   │   │   ├── comment-view.spec.ts
        │   │   │   └── comment-view.ts
        │   │   ├── dashboard-header
        │   │   │   ├── dashboard-header.css
        │   │   │   ├── dashboard-header.html
        │   │   │   ├── dashboard-header.spec.ts
        │   │   │   └── dashboard-header.ts
        │   │   ├── delete-post-popup
        │   │   │   ├── delete-post-popup.css
        │   │   │   ├── delete-post-popup.html
        │   │   │   ├── delete-post-popup.spec.ts
        │   │   │   └── delete-post-popup.ts
        │   │   ├── delete-user-popup
        │   │   │   ├── delete-user-popup.css
        │   │   │   ├── delete-user-popup.html
        │   │   │   ├── delete-user-popup.spec.ts
        │   │   │   └── delete-user-popup.ts
        │   │   ├── error-show
        │   │   │   ├── error-show.css
        │   │   │   ├── error-show.html
        │   │   │   ├── error-show.spec.ts
        │   │   │   └── error-show.ts
        │   │   ├── haeder-profile
        │   │   │   ├── haeder-profile.css
        │   │   │   ├── haeder-profile.html
        │   │   │   ├── haeder-profile.spec.ts
        │   │   │   └── haeder-profile.ts
        │   │   ├── hide-post-popup
        │   │   │   ├── hide-post-popup.css
        │   │   │   ├── hide-post-popup.html
        │   │   │   ├── hide-post-popup.spec.ts
        │   │   │   └── hide-post-popup.ts
        │   │   ├── login-nav
        │   │   │   ├── login-nav.css
        │   │   │   ├── login-nav.html
        │   │   │   ├── login-nav.spec.ts
        │   │   │   └── login-nav.ts
        │   │   ├── nav-bar
        │   │   │   ├── nav-bar.css
        │   │   │   ├── nav-bar.html
        │   │   │   ├── nav-bar.spec.ts
        │   │   │   └── nav-bar.ts
        │   │   ├── notif-view
        │   │   │   ├── notif-view.css
        │   │   │   ├── notif-view.html
        │   │   │   ├── notif-view.spec.ts
        │   │   │   └── notif-view.ts
        │   │   ├── post-form
        │   │   │   ├── post-form.css
        │   │   │   ├── post-form.html
        │   │   │   ├── post-form.spec.ts
        │   │   │   └── post-form.ts
        │   │   ├── post-report-component
        │   │   │   ├── post-report-component.css
        │   │   │   ├── post-report-component.html
        │   │   │   ├── post-report-component.spec.ts
        │   │   │   └── post-report-component.ts
        │   │   ├── post
        │   │   │   ├── post.css
        │   │   │   ├── post.html
        │   │   │   ├── post.spec.ts
        │   │   │   └── post.ts
        │   │   ├── posts-container
        │   │   │   ├── posts-container.css
        │   │   │   ├── posts-container.html
        │   │   │   ├── posts-container.spec.ts
        │   │   │   └── posts-container.ts
        │   │   ├── report-popup
        │   │   │   ├── report-popup.css
        │   │   │   ├── report-popup.html
        │   │   │   ├── report-popup.spec.ts
        │   │   │   └── report-popup.ts
        │   │   ├── report-user
        │   │   │   ├── report-user.css
        │   │   │   ├── report-user.html
        │   │   │   ├── report-user.spec.ts
        │   │   │   └── report-user.ts
        │   │   ├── succues-show
        │   │   │   ├── succues-show.css
        │   │   │   ├── succues-show.html
        │   │   │   ├── succues-show.spec.ts
        │   │   │   └── succues-show.ts
        │   │   ├── update-form
        │   │   │   ├── update-form.css
        │   │   │   ├── update-form.html
        │   │   │   ├── update-form.spec.ts
        │   │   │   └── update-form.ts
        │   │   ├── user-component
        │   │   │   ├── user-component.css
        │   │   │   ├── user-component.html
        │   │   │   ├── user-component.spec.ts
        │   │   │   └── user-component.ts
        │   │   ├── user-report-component
        │   │   │   ├── user-report-component.css
        │   │   │   ├── user-report-component.html
        │   │   │   ├── user-report-component.spec.ts
        │   │   │   └── user-report-component.ts
        │   │   ├── user-report-view
        │   │   │   ├── user-report-view.css
        │   │   │   ├── user-report-view.html
        │   │   │   ├── user-report-view.spec.ts
        │   │   │   └── user-report-view.ts
        │   │   └── user-view
        │   │   │   ├── user-view.css
        │   │   │   ├── user-view.html
        │   │   │   ├── user-view.spec.ts
        │   │   │   └── user-view.ts
        │   ├── create-post
        │   │   ├── create-post.css
        │   │   ├── create-post.html
        │   │   ├── create-post.spec.ts
        │   │   └── create-post.ts
        │   ├── dashboard
        │   │   ├── dashboard.css
        │   │   ├── dashboard.html
        │   │   ├── dashboard.spec.ts
        │   │   └── dashboard.ts
        │   ├── home
        │   │   ├── home.css
        │   │   ├── home.html
        │   │   ├── home.spec.ts
        │   │   └── home.ts
        │   ├── login
        │   │   ├── login.css
        │   │   ├── login.html
        │   │   ├── login.spec.ts
        │   │   └── login.ts
        │   ├── not-fount
        │   │   ├── not-fount.css
        │   │   ├── not-fount.html
        │   │   ├── not-fount.spec.ts
        │   │   └── not-fount.ts
        │   ├── notifications
        │   │   ├── notifications.css
        │   │   ├── notifications.html
        │   │   ├── notifications.spec.ts
        │   │   └── notifications.ts
        │   ├── pipe
        │   │   ├── format-date-pipe.spec.ts
        │   │   └── format-date-pipe.ts
        │   ├── post-report
        │   │   ├── post-report.css
        │   │   ├── post-report.html
        │   │   ├── post-report.spec.ts
        │   │   └── post-report.ts
        │   ├── post-view
        │   │   ├── post-view.css
        │   │   ├── post-view.html
        │   │   ├── post-view.spec.ts
        │   │   └── post-view.ts
        │   ├── profile
        │   │   ├── profile.css
        │   │   ├── profile.html
        │   │   ├── profile.spec.ts
        │   │   └── profile.ts
        │   ├── register
        │   │   ├── register.css
        │   │   ├── register.html
        │   │   ├── register.spec.ts
        │   │   └── register.ts
        │   ├── search
        │   │   ├── search.css
        │   │   ├── search.html
        │   │   ├── search.spec.ts
        │   │   └── search.ts
        │   ├── services
        │   │   ├── admin.spec.ts
        │   │   ├── admin.ts
        │   │   ├── comments.spec.ts
        │   │   ├── comments.ts
        │   │   ├── notifications.spec.ts
        │   │   ├── notifications.ts
        │   │   ├── post.spec.ts
        │   │   ├── post.ts
        │   │   ├── user.spec.ts
        │   │   └── user.ts
        │   ├── sub-post
        │   │   ├── sub-post.css
        │   │   ├── sub-post.html
        │   │   ├── sub-post.spec.ts
        │   │   └── sub-post.ts
        │   ├── update-post
        │   │   ├── update-post.css
        │   │   ├── update-post.html
        │   │   ├── update-post.spec.ts
        │   │   └── update-post.ts
        │   ├── user-report
        │   │   ├── user-report.css
        │   │   ├── user-report.html
        │   │   ├── user-report.spec.ts
        │   │   └── user-report.ts
        │   └── users
        │   │   ├── users.css
        │   │   ├── users.html
        │   │   ├── users.spec.ts
        │   │   └── users.ts
        ├── index.html
        ├── main.ts
        ├── model
        │   ├── MediaFile.model.ts
        │   ├── Notifaction.model.ts
        │   ├── Post.model.ts
        │   ├── User.model.ts
        │   ├── comment.model.ts
        │   └── images.ts
        ├── styles.css
        └── utils
        │   └── throttle.ts
    ├── tsconfig.app.json
    ├── tsconfig.json
    └── tsconfig.spec.json
```