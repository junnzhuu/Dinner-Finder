# G26 - Diner Finder Report

## **Table of Contents**

1. [Team Members and Roles](#team-members-and-roles)
2. [Summary of Individual Contributions](#summary-of-individual-contributions)
3. [Application Description](#application-description)
4. [Application UML](#application-uml)
5. [Code Design and Decisions](#code-design-and-decisions)
6. [Summary of Known Errors and Bugs](#summary-of-known-errors-and-bugs)
7. [Testing Summary](#testing-summary)
8. [Implemented Features](#implemented-features)
9. [Team Meetings](#meetings-records)
10. [Conflict Resolution Protocol](#conflict-resolution-protocol)

## Administrative

- Firebase Repository Link: [G26 Firebase Repo](https://console.firebase.google.com/u/0/project/gourmet-restaurant-search/firestore/data/~2F?hl=zh-cn)
    - Confirm: I have already added [comp21006442@gmail.com](mailto:comp21006442@gmail.com) as a Developer to the Firebase project prior to due date.
- Two user accounts for markers' access are usable on the app's APK (do not change the username and password unless there are exceptional circumstances. Note that they are not real e-mail addresses in use):
    - Username: [comp2100@anu.edu.au](mailto:comp2100@anu.edu.au)	Password: comp2100
    - Username: [comp6442@anu.edu.au](mailto:comp6442@anu.edu.au)	Password: comp6442

## Team Members and Roles

The key area(s) of responsibilities for each member

| UID | Name | Role |
| --- | --- | --- |
| [u7533831] | [Jing Li] | [Team Leader] |
| [u7603069] | [Jiawei Liu] | [Comprehensive Developer] |
| [u7574419] | [Catherine Jiawei Ye] | [Comprehensive Developer] |
| [u7602081] | [Jun Zhu] | [Requirement Document, UI design & Part of UI Implementation, Part of Front-end & Middleware, all Unit Test] |

## Summary of Individual Contributions

### 1. **u7533831, Jing Li**, I have 40% contribution, as follows:
- Code Contribution in the final App
    - **Feature**
        - [LogIn], [FB-Auth]
            - class: [LoginActivity.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/LoginActivity.java), [RegistrationActivity.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/RegistrationActivity.java), [FirebaseUtil.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Util/FirebaseUtil.java), [AuthenticationService.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Util/AuthenticationService.java), [FireStoreService.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Util/FireStoreService.java).
        - [DataFiles]
            - class: [MessageDataGenerator.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Simulator/HelperScripts/MessageDataGenerator.java), [RestaurantGenerator.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Simulator/HelperScripts/RestaurantGenerator.java), [SearchActionDataGenerator.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Simulator/HelperScripts/SearchActionDataGenerator.java).
        - [LoadShowData]
            - class: [MainSimulator.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Simulator/MainSimulator.java),
        - [Search]
            - class: [AbstractQueryParser.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/AbstractQueryParser.java), [AttributeConstraint.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/AttributeConstraint.java), [BasicQueryParser.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/BasicQueryParser.java),  [ParserFactory.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/ParserFactory.java), [Query.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/Query.java), [QueryParserFactory.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/QueryParserFactory.java), [RestaurantFilterQuery.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/RestaurantFilterQuery.java), [RestaurantQuery.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/RestaurantQuery.java), [RestaurantSearchFacade.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/RestaurantSearchFacade.java), [BTree.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/BTree.java), [RestaurantDatabase.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/RestaurantDatabase.java), [Rating.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Restaurant/Rating.java), [Restaurant.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Restaurant/Restaurant.java), [RestaurantType.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Restaurant/RestaurantType.java), [User.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Restaurant/User.java).
        - [P2P-DM] & [FB-Persist] & (Extension: [FB-synchronous])
            - Class: [RecentChatsActivity.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/RecentChatsActivity.java), [P2PChatActivity.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/P2PChatActivity.java), [MessageListenerService.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/BackGroundService/MessageListenerService.java), [MessageAdapter.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Adapter/MessageAdapter.java), [RatingAdapter.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Adapter/RatingAdapter.java), [RestaurantAdapter.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Adapter/RestaurantAdapter.java), [RestaurantViewHolder.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Adapter/RestaurantViewHolder.java), [ChatAdapter.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Adapter/ChatAdapter.java).
        
        Above is my individual work, and finished by myself alone.
        
        - [Interact-Micro]
            - [RatingDialogFragment.java#L61-80](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Fragment/RatingDialogFragment.java#L61-80),
        - [Interact-Follow]
            - [CommentHistoryActivity.java#L80-121](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/CommentHistoryActivity.java#L80-121)
        - [Data-Graphical]
            - [RestaurantDatabase.java#L229-306](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/RestaurantDatabase.java#L229-306)
        - [Search-Filter]
            - [FilterParser.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/FilterParser.java)
        
        Above feature is collaborated with teammates and made majority contribution (the code is shown in the ).
        
    - **Design Pattern**
        - Singleton Pattern - [RestaurantDataBase](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/RestaurantDatabase.java#L38-58)
        - Facade Pattern **-**  [RestaurantSearchFacade](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/RestaurantSearchFacade.java), [MainActivity](RestaurantSearchFacade)
        - Factory Pattern **-** [QueryParserFactory](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/QueryParserFactory.java), [ParserFactory](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/ParserFactory.java), [RestaurantSearchFacade](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/RestaurantSearchFacade.java#L42-46)
        - Template Pattern **-**  [AbstractQueryParser](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/AbstractQueryParser.java)
- **Code and App Design**
    - Design patterns, data structures
        - I finish the implementation of [B-Tree](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/BTree.java), [RestaurantDataBase](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/RestaurantDatabase.java#L43-88)
        - I design the code to use the following design pattern:
            - Singleton Pattern - [RestaurantDataBase](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/RestaurantDatabase.java#L38-58)
            - Facade Pattern - [RestaurantSearchFacade](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/RestaurantSearchFacade.java), [MainActivity](https://www.notion.so/RestaurantSearchFacade)
            - Factory Pattern - [QueryParserFactory](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/QueryParserFactory.java), [ParserFactory](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/ParserFactory.java), [RestaurantSearchFacade](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/RestaurantSearchFacade.java#L42-46)
            - Template Pattern - [AbstractQueryParser](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/AbstractQueryParser.java)
        
        Those are my individual work.
        
    - UI Design
        - [activity_login.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_login.xml), [activity_p2p_chat.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_p2p_chat.xml), [activity_recent_chats.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_recent_chats.xml), [activity_registration.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_registration.xml), [activity_restaurant_detail.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_restaurant_detail.xml), [chat_message_item.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/chat_message_item.xml), [item_chat.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/item_chat.xml), [restaurant_item.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/restaurant_item.xml)
        
        Those are my original design
        
        - [activity_main.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_main.xml), [nav_header.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/nav_header.xml), [item_rating.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/item_rating.xml)
        
        Those are refactored by my team mate after I first wrote it.
        
- **Others**
    - Report 
    I wrote the Application Description, Code Design and Decisions, Data Structures, Design Patterns, Tokenizers and Parsers, Modular Development Approach, Basic Features, Peer to Peer Messaging [P2P-DM], Firebase Integration part of the report.
    - I build the APK, setups the project and firebase, and finish the UML.

### 2. **u7602081, Jun Zhu,** I have 30% contribution, as follows:
- **Code Contribution in the final App**
    - Feature Implementation:
        - [Data-Profile]
            - method: [MainActivity.java #L140-150: setupDrawerNavigation()](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L140-150), [MainActivity.java #L211-219: initNavigationView()](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L211-219), [MainActivity.java #L234-251: onBackPressed()](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L234-251), [MainActivity.java #L424-468: logoutMenu()](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L424-468)
                
                My responsibilities included designing and implementing the UI for the profile navigation menu, implementing the functionality to open or close the profile navigation menu, adding the functionality to navigate from the profile page to the comment history page, and adding a confirmation dialog for the "Logout" feature when clicked. Jiawei Ye is responsible for editing and synchronizing the sections related to usernames and email addresses, and GPS part.
                
        - [Data-Graphical]
            - class:  [BarChartFragment.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Fragment/BarChartFragment.java), [LineChartFragment.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Fragment/LineChartFragment.java), [PieChartFragment.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Fragment/PieChartFragment.java), [RadarChartFragment.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Fragment/RadarChartFragment.java), [ViewPagerAdapter.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Adapter/ViewPagerAdapter.java), [ChartsActivity.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/ChartsActivity.java), [Colors.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Resources/Colors.java), [Constants.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Resources/Constants.java)
            - method: [RestaurantDatabase.java #230-306: extractData()](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/RestaurantDatabase.java#L229-306),  [MainActivity.java #L323-333: initFabButton()](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L323-333)
                
                I have fully implemented the front-end and middleware adapter components of Data-Graphical, designed and implemented the user interface. By utilizing the MPAndroidChart library, I have integrated the Data-Graphical feature by calling the method: [RestaurantDatabase.java #230-306: extractData()](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/RestaurantDatabase.java#L229-306), which was collaboratively developed with Jing Li. I have also designed a floating button on Profile page for navigation to the ChartsActivity.
                
        - [Interact-Micro]
            - class: [RatingDialogFragment.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Fragment/RatingDialogFragment.java)
            - method: [RestaurantDetailActivity.java #L167-183: showRatingDialog()](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/RestaurantDetailActivity.java#L167-183)
                
                I implemented the functionality to add ratings and reviews for restaurants on the RestaurantDetail page. In this feature, my responsibilities included designing and implementing the UI for the dialog, as well as handling the front-end aspects. Jing Li was responsible for the back-end and database aspects of this feature.
                
        - [Interact-Follow]
            - class:  [CommentHistoryActivity.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/CommentHistoryActivity.java), [CommentHistoryAdapter.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Adapter/CommentHistoryAdapter.java), [CommentModel.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Restaurant/CommentModel.java)
                
                In this feature, my responsibilities included designing and implementing the UI, handling the frontend and adapter components, and partially implementing the backend. I accomplished the organization of historical reviews by categorizing them according to restaurants through the nesting of two RecyclerViews.
                
    - Unit Test:
        
        I create all the Unit Test cases and the coverage of the test is 81%, which is above 75%. all the test classes in test file:
        
        [https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/tree/main/src/app/src/test/java/G26/Project/Model](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/tree/main/src/app/src/test/java/G26/Project/Model)
        
        Design: I create a test class for each class implemented at the Back-End except for `FilterParser`, `QueryParserFactory`, `RestaurantFilterQuery` and `RestaurantSearchFacade`, because these four classes need to use firebase, which is not contained in the unit test part.
        
    - Design Pattern:
        - Adapter Pattern :
            - [CommentHistoryAdapter.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Adapter/CommentHistoryAdapter.java): CommentHistoryAdapter adapts the CommentModel data to each view item in the RecyclerView.
            - [ViewPagerAdapter.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Adapter/ViewPagerAdapter.java): FragmentStateAdapter itself is an application of the adapter pattern. It adapts data to user interface elements, making it possible to display multiple fragments in a ViewPager2. Here, the ViewPagerAdapter provides different fragments for ViewPager2.
        - Observer Pattern:
            - [CommentHistoryAdapter.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Adapter/CommentHistoryAdapter.java): By calling method: notifyDataSetChanged(), the RecyclerView is able to know that its data has changed and subsequently refreshes the interface. In this case, the RecyclerView acts as an observer, watching for changes in its data (i.e., the list of comments in the CommentHistoryAdapter).
        - ViewHolder Pattern:
            - [CommentHistoryAdapter.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Adapter/CommentHistoryAdapter.java): RecyclerView uses ViewHolder to cache views to avoid frequent calls to findViewById.
        - Factory Pattern:
            - [ViewPagerAdapter.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Adapter/ViewPagerAdapter.java): The createFragment() method returns a new Fragment instance based on the provided position. This is a simple application of the factory pattern, as this method generates an object of a particular type for the requester at runtime.
    - Design and Implementation of UI:
        - Data-Graphical (Data Charts): [activity_charts.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_charts.xml), [fragment_bar_chart.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/fragment_bar_chart.xml), [fragment_data_chart.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/fragment_data_chart.xml), [fragment_line_chart.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/fragment_line_chart.xml), [fragment_pie_chart.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/fragment_pie_chart.xml), [fragment_radar_chart.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/fragment_radar_chart.xml)
        - Data-Profile: [activity_drawer.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_drawer.xml), [nav_menu.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/menu/nav_menu.xml), [nav_header.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/nav_header.xml), [activity_main.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_main.xml) (part of it)
        - Interact-Micro (Rating Dialog): [activity_rating_dialog.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_rating_dialog.xml)
        - Interact-Follow (Comment History): [activity_comments_history.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/activity_comments_history.xml), [fragment_past_comments.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/fragment_past_comments.xml)
        - Bottom Navigation Menu: [bottom_navigation_menu.xml](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/menu/bottom_navigation_menu.xml)
    - Refactor the code:
    I created [Constants.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Resources/Constants.java) and [Colors.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Resources/Colors.java) to reduce Hardcode in the code, Constants.java was co-written by myself and Jiawei Liu.
- **Code and App Design**
    - Design Pattern used: 
      - Adapter Pattern, Observer Pattern, ViewHolder Pattern, Factory Pattern.
    - Data Structure used: List, Map
    - Unused UI:
      - I initially designed a set of UI using Figma, whose link is below, but encountered obstacles when trying to export assets and import them into Android Studio. Ultimately, considering the time constraints, I decided to abandon this UI design.
    [https://www.figma.com/file/fBCFIkAjUVpJyzl0W0MFTa/DineFinder---A-Smart-Restaurant-Search-App?type=design&mode=design&t=S2ZvJzj3rzKS2STl-1](https://www.figma.com/file/fBCFIkAjUVpJyzl0W0MFTa/DineFinder---A-Smart-Restaurant-Search-App?type=design&mode=design&t=S2ZvJzj3rzKS2STl-1)
    - Unused JSON writer:
      - In the commit: [a232d99c57dc19639b432f7a3d3ecd0388784844](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/commit/a232d99c57dc19639b432f7a3d3ecd0388784844), I implemented a function(JsonUtils.java) to save the user's search history in local memory in order to implement the Data-Format feature later, but ended up abandoning the feature due to running out of time.
- **Others**:
    - Report I wrote:
        - Implemented features: Data-Graphical, Interact-Follow and Interact-Micro
        - Test Summary
    - Documentation:
        - I wrote the [Requirement Documentation](https://www.notion.so/DineFinder-Requirement-Document-D2-0-1d5dce63f748459180174486566b69e1?pvs=21) base on the meeting content.
        - I draw the flowchart of the application.
    - Video demo:
        - Wrote the transcripts for the Comment History and Data Charts sections
    - Firebase:
        - I added 45 photos of restaurants to firebase, wrote a Python script to batch-compress the image sizes.
- **Author Correction**
    
    The last version of code below that was submitted before the deadline had an incorrect author, which should be me(Jun Zhu u7602081).
    
    - [MainActivity.java #L140-150: setupDrawerNavigation()](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L140-150)
    - [RatingDialogFragment.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Fragment/RatingDialogFragment.java) (whole class except for method: handleRatingSubmission)
    
### 3. **U7603069, Jiawei Liu**, I have a 25% contribution, as follows:
- Code Contribution in the final App
    - Feature
        - [Filter]
            - class:  [FiterDialogManager](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Managers/FilterDialogManager.java),  [MainActivity#L152-169](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L152-169), [FilterPaser](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/FilterParser.java)
        - [Search]
            - class: [Token](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/Token.java), [QueryTokenizer](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/QueryTokenizer.java), [Pair](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/Pair.java)
- Instruction function.
- Design and Implementation of UI:

    FilterBox

    Made improvements to most of the UI interfaces, including buttons, layout design, backgrounds, and added embellishments.

- Refactor the code:

    Half of the  [Constants.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Resources/Constants.java) Workload.

    Optimized all hardcodes of UI files.

- **Others**:
    - Search part preliminary ideas(part of them was discarded)
    - Report I wrote:
        - Implemented features: Filter
        - Grammar
        - User case
        - Test Search part.
    - Video demo:
        - Wrote the transcripts for all content except the simulator part.
        - Record video.
        - Add subtitle.
        - Edit the video.
    
### 4. **u7574419, Jiawei Ye**, I have 20% contribution, as follows:
**Code Contribution in the final App**
- Features [Data-Profile], [Data-GPS], [Logout]
- Implemented the user profile functionality, which dynamically displays and syncs the user's data with Firebase.
    
    [Class “[NavigationManager](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Managers/NavigationManager.java#L1-98)”, methods “initNavigationView”] 
    
    Feature: [Edit Username](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/res/layout/nav_header.xml#L22-39) in the navigation sidebar and sync it with the database
    
- Enabled the GPS feature to show the user's current city and integrated the "Around Me" filter for localised search results.
    
    [Class “Main Activity”, methods “[getLatitude](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L276-303)”, “[getLongitude](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L276-303)”] 
    
    [Class “NavigationManager”, methods “[initNavigationView](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Managers/NavigationManager.java#L101-114)”] 
    
- Developed the logout feature ensuring user session management and security.
    
    [Set an on-click listener for the logout](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L221-231)
    
    **Code and App Design**
    
    - Display of the username, user email and user avatar, and location (i.e. city) in the navigation sidebar from the data of the Firestore Database.
    
    **Others**
    
    - I wrote the “UserLogin”, “User Profile”, “User-GPS” and “Firebase Authentication” parts of the report and imported all our meeting minutes under meeting record section.
    - UML: I worked with Jun Zhu to draw the process diagram.

## **Application Description**

*Diner Finder* is a versatile restaurant discovery and information application designed to cater to the diverse needs of food enthusiasts, travelers, and anyone seeking dining options. It simplifies the process of finding restaurants in a specific location, providing essential details such as average price per person, ratings, and quick access to restaurant information sorted by various criteria, including restaurant category, geographic location, and name.

### System Architecture:

![System Architecture](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/raw/main/items/App_Architecture_diagram.png)

### Product Flow Chart:

![Product Flow Chart](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/raw/main/items/App_product_flow_chart.png)

### **Application Use Cases and or Examples**

![Product Use Case Diagram](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/raw/main/items/App_use_case.png)

**1. Find a restaurant based on budget and type**   *Anna wants to find a suitable Italian restaurant in her city, but she doesn't want it to be too cheap for fear of a poor experience. Her budget is between 20 and 50 Australian dollars.*

- Anna opens the restaurant search app and lands on the main page.
- In the filter function, she sets the minimum price to 20 Australian dollars, and the maximum price to 50 Australian dollars, chooses "Italian" in the Category, and selects her current city in the city field.
- The app displays all Italian restaurants priced between 20-50 Australian dollars.
- She chooses a highly-rated restaurant and reads user reviews to confirm her choice.

**2. Explore nearby restaurants**   *Tom is on a business trip and wants to find the highest-rated nearby Japanese restaurant.*

- Tom opens the app and uses the GPS location feature to find nearby restaurants.
- He uses the filter function to select "Japanese" as the restaurant type, checks the "around me" option, and selects "Most Reviews First."
- The app displays a list of restaurants that meet the criteria, and Tom chooses a restaurant with high popularity and a rating of 4.5.

**3. Direct search through the search bar**   *Marie wants to search for a restaurant named Dine Fine in Chicago.*

- Marie first checks the circular icon with a question mark on the main interface, which reveals an input tutorial when clicked.
- Marie searches for the restaurant's name according to the prescribed format and finds several restaurants with the same name.
- Marie then enters "Chicago" in the search box following the prescribed format.
- Marie finds the restaurant she was looking for.

**4. View restaurant data statistics**   *Ella is a food blogger and wants to analyze the distribution of the number of restaurants, ratings, prices, and types on the app.*

- Ella opens the app and goes to the filter interface, selecting her desired city from the filtering menu.
- She clicks on the circular icon in the bottom right corner representing statistical data.
- The app displays charts on the number of restaurants in various cities, distribution by price range, distribution by rating, and the total number of each restaurant type.

**5. P2P chat consultation**   *Lucas wants to discuss a restaurant's menu with his friend Ivy.*

- Lucas searches for Ivy's user ID in the app and initiates a chat.
- Ivy can easily find her previous chat with Lucas in the chat interface's history.
- They share their views on the restaurant during the chat.

---
### Application UML
![Application UML](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/raw/main/items/App_UML.png)

---

## **Code Design and Decisions**

### **Data Structures**

1. **HashMap**
    - *Objective:* Efficiently categorize restaurants based on their type.
    - *Code Locations:* [RestaurantDataBase](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/RestaurantDatabase.java#L43-88)
    - *Reasons:* The decision to use a HashMap stems from its ability to store data in key-value pairs, allowing O(1) time complexity for data retrieval, which is paramount for performance in searches. By using restaurant types as keys, we can swiftly access a collection of restaurants of a specific type, thereby narrowing down the search scope and significantly reducing the search time compared to a linear search through an unstructured list.
2. **B-tree**
    - *Objective:* To store restaurant data in an organized manner that facilitates quick, efficient range queries for operations like searching.
    - *Code Locations:* [B-Tree](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/BTree.java), [RestaurantDataBase](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/RestaurantDatabase.java#L43-88)
    - *Reasons:* B-trees are particularly suited for our needs due to their ability to hold multiple keys per node and maintain data sorted, which expedites search operations. For range searches, like finding all restaurants with costs greater than a certain value, B-trees are superior because they allow us to skip large swaths of irrelevant data. Instead of traversing every element as we would in a linearly organized structure, we can traverse the tree downwards, directly honing in on the relevant range. This structure is especially advantageous for disk-based storage or scenarios where data is larger than the memory, like databases, due to minimal disk I/O operations.
3. **ArrayList**
    - *Objective:* To store and manage collections of objects such as restaurant information, reviews, and messages that will be displayed in the UI.
    - *Code Locations:* [RestaurantAdapter](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Adapter/RestaurantAdapter.java#L28-91), [initialView & searchInput](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Adapter/RestaurantAdapter.java#L28-91)
    - *Reasons:* ArrayList was chosen over alternatives like LinkedList because of its efficacy in handling storage and retrieval operations. ArrayLists allow O(1) time complexity for retrieval, which is crucial for performance when accessing items to be displayed in the UI. They are also more memory efficient as they don't have the overhead of node references required in linked structures. Furthermore, ArrayLists are dynamically resizable, yet they provide the benefit of being backed by an array, which makes them preferable in scenarios where there's a need for extensive read operations and less frequent insertions and deletions, typical of UI display tasks. Comparatively, while LinkedLists facilitate faster modifications (additions, deletions), they have a linear time complexity for retrieval operations, making them less efficient for our use case.

---

### **Design Patterns**

1. **Singleton Pattern**
    - *Objective:* Used for storing restaurant data.
    - *Code Locations:* [RestaurantDataBase](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/RestaurantDatabase.java#L38-58)
    - *Reasons:*
        - *Controlled Access:* The Singleton pattern ensures a single shared instance of an object, providing a global point of access to the restaurant data. It is particularly beneficial in our client-server architecture where all restaurant data is loaded at once, avoiding the need for repeated memory requests that could lead to heap overflow, especially with large data sets.
        - *Lazy Initialization and Thread Safety:* We implemented the Singleton to initialize the database instance only when it's needed for the first time, preventing unnecessary memory usage. It can also be made thread-safe, ensuring data integrity in multi-threaded environments.
2. **Facade Pattern**
    - *Objective:* Used for providing a unified entry point for the search feature.
    - *Code Locations:* [RestaurantSearchFacade](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/RestaurantSearchFacade.java), [MainActivity](RestaurantSearchFacade)
    - *Reasons:*
        - *Simplicity:* The Facade pattern simplifies complex systems by providing a high-level interface, making the system easier to use. For our search feature, integrating tokenizer, parser, and B-tree support becomes more manageable and user-friendly.
        - *Decoupling:* It helps in decoupling the system from the client and other subsystems, thereby reducing the potential for ripple effects across the system.
3. **Factory Pattern**
    - *Objective:* Used for creating different parsers based on the type of input.
    - *Code Locations:* [QueryParserFactory](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/QueryParserFactory.java), [ParserFactory](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/ParserFactory.java), [RestaurantSearchFacade](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/RestaurantSearchFacade.java#L42-46)
    - *Reasons:*
        - *Flexibility:* The Factory pattern provides a way to encapsulate the instantiation process. It's useful in our case where different types of parsers are required based on input. For instance, a text search input requires a different parser compared to a value-selected filter input.
        - *Extensibility:* If we decide to introduce new search criteria, like city or cuisine, we can simply extend our existing codebase and create a new parser, without modifying the existing code, adhering to the open/closed principle.
4. **Template Pattern**
    - *Objective:* Used to define the parser skeleton of an algorithm in an operation.
    - *Code Locations:* [AbstractQueryParser](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/AbstractQueryParser.java)
    - *Reasons:*
        - *Standardization and Predictability:* The Template Method pattern is crucial for standardizing the structure and sequence of operations. The **`parse()`** method dictates a fixed sequence of operations (**`initialize()`**, **`doParsing()`**, **`finalizeParsing()`**), ensuring a consistent process flow.
        - *Extensibility:* While the Template Method pattern ensures adherence to a specific sequence, it allows subclasses to implement or extend the steps differently. This means that the actual parsing logic can vary significantly without disrupting the fundamental process flow. It's particularly useful when the application handles different types of parsing or when the parsing logic might change in the future, offering a great deal of flexibility within a rigid, reliable structure.
        - *Code Reuse and Separation of Concerns:* This pattern promotes code reuse and helps in organizing code according to the separation of concerns principle. Each method (**`initialize()`**, **`doParsing()`**, **`finalizeParsing()`**) has its own responsibility, which makes the code cleaner and more maintainable. It also means common code can go into the base class methods (potentially the **`parse()`** method itself), while specific, detailed logic resides in the subclasses.

---

### **Parser**

### **Grammar(s)**

The purpose of the grammar designed in the provided classes is to parse and evaluate a restaurant query. This grammar provides an abstraction to structure restaurant-related search queries which include attributes like city, restaurant name, category, cost, and rating. It aims to simplify the process of querying the database with specific restaurant-related attributes.

Advantages of the design:

1. **Flexibility**: The grammar can easily be extended to support additional attributes or operators.
2. **Clarity**: By defining clear tokens and production rules, the grammar avoids ambiguities and can accurately parse a variety of user inputs.
3. **Modularity**: The design follows the Single Responsibility Principle, meaning each class has a clear and singular purpose. This makes it easy to update, maintain, and extend.
4. **Error Handling**: Malformed queries or unsupported tokens will trigger exceptions, ensuring that invalid input is caught and handled appropriately.
5. **Extensibility**: The use of the Template Method pattern in **`AbstractQueryParser`** and the Factory pattern in **`ParserFactory`** makes it easier to add new types of parsers or modify the parsing logic in the future.

**Production Rules**:

Based on the provided code, here are the production rules for the grammar:

1. **<expressions>** ::= <factors> | <factors> , <expressions>
    - Represents a set of factors, possibly separated by commas.
2. **<factors>** ::= <factor> | <factor>, <factors>
    - Represents one or more factors.
3. **<factor>** ::= <attribute> <operator> <value>
    - Represents the basic unit of the query, consisting of an attribute, an operator, and a value.
4. **<attribute>** ::= @ | # | $ | ! | /
    - Represents different attributes that can be queried: city (@), restaurant name (#), cost ($), rating (!), category (/).
5. **<operator>** ::= == | < | >
    - Represents comparison operators.
6. **<value>** ::= STRING | NUMBER | RESTAURANT_TYPE
    - Represents the value of an attribute. Can be a string (like city names or restaurant names), a number (like cost or rating values), or a type of restaurant (e.g., Italian, Chinese, etc.)

From these production rules, it's clear how different queries will be parsed. For instance, a query like **`@ == "New York"`** would match the pattern **`<attribute> <operator> <value>`**, with **`@`** as the attribute, **`==`** as the operator, and **`"New York"`** as the value.

### **Tokenizers and Parsers**

**Tokenizer Implementation:**

In our app, the tokenizer is used in the initial stage of processing the user's search query. The [QueryTokenizer](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/QueryTokenizer.java) class is a critical component that breaks down the input query strings into more manageable pieces, known as tokens, which are then used for further processing and parsing.

**How It Works:**

1. **Regular Expressions:** The tokenizer uses a predefined set of regular expressions stored in **`REGEX`** to identify different possible entities in a search query. These entities can be special characters, operators, quoted strings, numbers, restaurant types, or other strings.
2. **Token Identification:** Using Java's **`Pattern`** and **`Matcher`** classes, the tokenizer scans the input string and matches segments of this string against the provided regular expressions. Each valid segment found is considered a token.
3. **Token Categorization:** Once a token is identified, the **`getTokenType`** method is used to categorize the token based on its content. It distinguishes between various types of tokens like cities, categories, costs, ratings, restaurant names, and more, each represented by a specific symbol or string pattern.
4. **Token Pair Creation:** For each token identified and categorized, a pair consisting of the token string and its type is created and added to a list of tokens, which will be returned for further processing.

**Reasons:**

- **Simplicity and Clarity**: Utilizing regular expressions delineates clear, manageable rules for string matching, essential for the diverse nature of user inputs.
- **Flexibility**: Our system accommodates an extensive array of input formats, evident from the categorization of tokens into various types like **`CITY`**, **`CATEGORY`**, **`COST`**, and **`RATING`**.
- **Error Handling**: The tokenizer's capability to classify inputs into distinct token types enhances the system's ability to detect and manage erroneous or unexpected inputs, ensuring a smooth user experience.

**Parser Implementation**

The app manages this complex search process using two specialized parsers: [BasicQueryParser](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/BasicQueryParser.java) and [FilterParser](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/FilterParser.java). Notably, **`FilterParser`** extends from **`BasicQueryParser`**, indicating a specialized use case and enhancement over the basic functionality.

**Working Mechanism:**

- **Initialization**:
    - **`BasicQueryParser`** initializes with a list of tokens. It's primarily used for parsing text search input where the user's query is tokenized, and each token represents part of the search criteria.
    - Contrarily, **`FilterParser`** doesn't use tokenized inputs. Instead, it initializes with user-selected filter values and a context from **`MainActivity`**. It's utilized for situations where users apply specific filters to their search, such as location, rating, and cost.
- **Parsing Process**:
    - The **`doParsing`** method in **`BasicQueryParser`** is designed to handle tokenized inputs, interpreting the user's text query. The parser expects tokens in the format of expressions, where each expression consists of an attribute, an operator, and a value. These are represented by the **`<attribute> <operator> <value>`** structure in the grammar rules. Expressions are separated by commas, and the method processes each token until there are no more tokens available.
    - **`FilterParser`**, while extending **`BasicQueryParser`**, overrides the parsing methods to accommodate the user-selected filters directly. It sets query parameters without requiring a tokenized input, i.e., checking if certain filters are selected (like "around me" for geolocation), retrieving user-selected values (like min/max cost, min/max rating, restaurant type, and sorting condition), and applying these values as constraints in the **`RestaurantFilterQuery`**
- **Attribute Application**:
    - **`BasicQueryParser`** utilizes the **`applyAttribute`** method to construct conditions in the **`RestaurantQuery`** based on parsed input. It comprehensively manages different attribute types and applies corresponding constraints.
    - **`FilterParser`** takes a more direct approach by applying the user-selected filters to the **`RestaurantFilterQuery`**. It integrates advanced features like geolocation and handles various filter types, translating them into query constraints.
- **Error Handling**:
    - Both parsers implement robust error handling mechanisms. They validate the presence of essential components in the query, whether tokens in **`BasicQueryParser`** or filter selections in **`FilterParser`**, and raise exceptions when anomalies are detected. This precaution ensures the construction of only valid queries.
- **Finalization**:
    - The process culminates with the **`finalizeParsing`** method, delivering the constructed query object. For **`BasicQueryParser`**, it's a **`RestaurantQuery`**, whereas for **`FilterParser`**, it's a **`RestaurantFilterQuery`**, both tailored to their input specifications.

---

### **Others**

### **Modular Development Approach**

One of the significant architectural decisions we embraced throughout the development of our application was the adoption of a modular development approach. This methodology profoundly influenced the way our codebase was structured and, consequentially, how efficiently our team could operate.

- **Reasons:**
    - **Code Organization and Readability:** By segmenting our application into discrete, functionally distinct modules, we've been able to maintain a high level of organization and readability within our code. Each component and function in the main has been modularized, ensuring that every segment of code has a clear purpose and functionality. This clarity not only makes the codebase more navigable for developers but also simplifies the process of identifying the relationships and dependencies between different parts of the application.
    - **Simplified Debugging and Maintenance:** This has been invaluable during the debugging process. By having the ability to isolate specific modules, especially in the [onCreate](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L105-130) method, we could systematically comment out segments of code and execute the application part by part. This technique allowed us to perform targeted testing and quickly identify the root cause of bugs and issues, significantly speeding up the troubleshooting and resolution process.

---

## **Implemented Features**



### **Basic Features**

1. **[Login]:** In the "Dine Finder" app, the Login feature is essential as the initial access point. Without registering and logging in, users cannot access the app's content, including viewing restaurants.
    
    **Code:** 
    
    [Class “[LoginActivity](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/LoginActivity.java#L79-L103)”, methods “[handleLogin](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/LoginActivity.java#L79-87)”, “[handleAuthenticationResult](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/LoginActivity.java#L88-104)”, “[navigateToMainUI](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/LoginActivity.java#L105-112)”] 
    
    **Description of feature:** Users can log into an existing account or register for a new one. Specifically, the “Login” feature implemented involves user authentication for our “Dine Finder” app, allowing users to log in with their email address and password. Upon successful authentication, users are redirected to the main application interface. We have included the two accounts for markers access to our app. 
    
    **Description of implementation:** 
    
    - Leveraged Firebase Authentication for secure user login.
    - Initialised Firebase in the `LoginActivity` class.
    - Set up UI components and event listeners in `LoginActivity`.
    - Validated user input on login attempt through `handleLogin` method.
    - Used `AuthenticationService` (singleton) for actual Firebase interaction.
    - `AuthenticationService` returns LiveData object with authentication result, enabling UI to react accordingly.
    - On successful authentication, `handleAuthenticationResult` navigates user to the application's main interface.
    - UI is defined in XML layout (i.e. activity_login), using `ConstraintLayout` for adaptable UI design.
    - Included email and password input fields, and login/registration buttons in the layout.
    - Employed custom artistic font and drawable resources for enhanced aesthetics.
    - Ensured secure, efficient, and user-friendly authentication, enhancing user experience and data security.
2. **[DataFiles]**. In order to simulate different users’ interactions on with the Diner Finder app, we have simulated three distinct types of data: Peer-to-Peer (P2P) messaging, restaurant ratings, and search keywords. In total, these simulated datasets comprise 3,000 entries, with each category containing 1,000 items.(easy)
    - **Storage Solution:** We use Firebase's Firestore Database as our storage medium. Each type of simulated data is stored as individual documents within its dedicated Firestore collection.
    - **Link to the Firebase repo**: [P2P_Data](https://console.firebase.google.com/project/gourmet-restaurant-search/firestore/data/~2FP2P?hl=zh-cn&consoleUI=FIREBASE), [Ratings_Data](https://console.firebase.google.com/project/gourmet-restaurant-search/firestore/data/~2FRatings?hl=zh-cn&consoleUI=FIREBASE), and [Search_Data](https://console.firebase.google.com/project/gourmet-restaurant-search/firestore/data/~2FSearchData?hl=zh-cn&consoleUI=FIREBASE).
    - **Data Generation Method**: To streamline the process of data generation and uploading, we developed a set of helper scripts:
        - [MessageDataGenerator.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Simulator/HelperScripts/MessageDataGenerator.java) : Responsible for generating simulated P2P messages and ratings. Notably, the dummy [messages](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Simulator/HelperScripts/MessageDataGenerator.java#L98-228) were produced with the assistance of ChatGPT 4.0.
        - [RestaurantGenerator.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Simulator/HelperScripts/RestaurantGenerator.java) : Generates simulated restaurant ratings.
        - [SearchActionDataGenerator.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Simulator/HelperScripts/SearchActionDataGenerator.java) : Crafts simulated search keyword entries.

1. **[LoadShowData].** When a user is logged in, load simulation data from Firebase at regular time intervals (10 seconds), and visualize the same in the App.
    - **Code:** [MainSimulator](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Simulator/MainSimulator.java), [RatingAdapter](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Adapter/RatingAdapter.java), [RestaurantViewHolder](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Adapter/RestaurantViewHolder.java), [MainActivity](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L390-422)
    - **Description of feature:** This feature dynamically updating and visualizing data within the app at a regular interval. It operates by incrementally displaying more items, such as search action, and simulating user interactions like peer-to-peer messages or comments.
    - **Description of the implementation:** Our approach employs a client-server architecture with Firebase serving as the data backbone. Following user authentication, restaurant [data](https://console.firebase.google.com/project/gourmet-restaurant-search/firestore/data/~2Frestaurants?hl=zh-cn&consoleUI=FIREBASE) is retrieved from Firebase and stored locally within a Nested B-tree structure for efficient access and visualization.
        - **Simulated Interactions:** Post initial data retrieval, the [MainSimulator](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Simulator/MainSimulator.java) orchestrates a series of simulated user actions—searches, comments, and peer-to-peer messages. These actions, derived from our pre-constructed simulation data ([P2P_Data](https://console.firebase.google.com/project/gourmet-restaurant-search/firestore/data/~2FP2P?hl=zh-cn&consoleUI=FIREBASE), [Ratings_Data](https://console.firebase.google.com/project/gourmet-restaurant-search/firestore/data/~2FRatings?hl=zh-cn&consoleUI=FIREBASE), and [Search_Data](https://console.firebase.google.com/project/gourmet-restaurant-search/firestore/data/~2FSearchData?hl=zh-cn&consoleUI=FIREBASE)), are executed sequentially at intervals of 10 seconds.
        - **Real-time Visualization**: Each simulated action triggers specific functionalities within the app:
            - Searches invoke the [searchInput](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L355-375) method, replicating user-initiated searches.
            - Peer-to-peer messages and comments leverage [sendMessage](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Util/FireStoreService.java#L150-203)and [saveRatingToDatabase](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Util/FireStoreService.java#L94-148) methods, respectively. These methods mirror the actual procedures users follow, thus uploading new data to Firebase.
        - **Data Synchronization:** We integrated Firebase's real-time listening capabilities when first [loading data](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L390-422) from firebase, ensuring continuous data synchronization. This allows for immediate local updates in response to changes in the cloud database after we send the simulation data back to firebase, particularly concerning restaurant information and chat messages, and thereby maintaining consistency across user sessions.
    
    This implementation ensures that all app users experience real-time updates, contributing to a dynamic and shared user environment.
    

1. **[Search].** Users are able to search for restaurant information on our app. (medium)
    - **Code:** [BTree](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/BTree.java), [RestaurantDatabase](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/RestaurantDatabase.java), [AbstractQueryParser](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/AbstractQueryParser.java), [BasicQueryParser](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/BasicQueryParser.java), [QueryTokenizer](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/QueryTokenizer.java),
    - **Description of feature:** The "Search" feature is an integral component of our application, designed to enable users to efficiently locate restaurants based on specific criteria such as restaurant type and average cost per person. This feature is particularly optimized for these common use-cases.
    - **Description of our Implementation**: Our implementation of the search functionality is sophisticated, incorporating several design patterns and a custom search algorithm to ensure efficiency and accuracy.
        - **Singleton Pattern for Data Management**: Utilizing the Singleton design pattern, we ensure that our [restaurant database](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/RestaurantDatabase.java#L49-59) is instantiated only once throughout the app's lifecycle. This strategy is crucial given our client-server architecture, where all restaurant data is loaded into memory when the app launches. Duplication of database instances would pose significant memory risks, hence the need for this pattern. The database is instantiated within the [onCreate](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L114) during the [MainActivity](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L390-422) method.
        - **Factory, Template and Facade Patterns for Search Processing**: We employ the [Factory design pattern](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/QueryParserFactory.java) to generate different parsers, contingent on the presence of user input tokens. This is crucial as search and filter operations are fundamentally similar, yet filtering doesn't require input tokens - it operates based on selected filters. The [Template pattern](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/AbstractQueryParser.java#L32-41) is used to define a uniform interface that outlines common operational steps for these parsers, ensuring consistency and reusability. Importantly, we also implement the [Facade pattern](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/RestaurantSearchFacade.java), providing a simplified interface for the underlying complexity of the search functionality. This facade centralizes the search logic, making it easier for other parts of the application to initiate searches without needing to understand the inner workings of the search process, tokenization, or parsing phases.
        - **Optimized Search with B-Tree**: The search operation is significantly optimized using a B-tree structure. This structure allows for speedy access to data, essential for real-time applications. The [searchWithPredicate](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/BTree.java#L88-135) method is pivotal here, enabling search operations based on specific predicates (conditions) and range checks. This method is particularly efficient for cost-based searches, as it leverages the sorted nature of B-trees to quickly discard irrelevant sections of the tree.
    - **Code Explanation**:
    The [searchWithPredicate](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/BTree.java#L88-135) function is a recursive method designed to perform efficient range-based searches in the B-tree. Here's a detailed breakdown:
        - It begins by checking the current node. If the node is null, the recursion ends.
        - It then iterates over all the keys in the current node, checking each key against a provided predicate. If a key satisfies the predicate, it's added to the result set.
        - The method utilizes a range check function to decide whether to explore a node's child nodes. This is where the B-tree's efficiency comes into play: by comparing the current key with the extreme keys in the node (leftmost and rightmost), the function determines whether any children may contain keys within the desired range. If they do, the function recursively searches those children. If not, it skips them, saving time.
        - This process continues until all relevant keys are checked, and all potential children are explored.
        
        This method, thus, ensures that our searches are not only accurate but also exceedingly efficient, even with large datasets. The range check optimization is particularly crucial as it significantly reduces the number of nodes explored, directly correlating to faster search times and reduced resource usage.
        
    - **How the different components work together**
        - **Start of the Search Process:** The process begins in the main activity where the [searchInput()](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L335-353) function captures the user's query.
        - **Use of the Facade Pattern**: This input is then passed to an instance of [RestaurantSearchFacade](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/RestaurantSearchFacade.java#L36-48), which serves as the entry point to the search system.
        - **Parsing the Search Input:** The facade then interacts with a parser created by a parser factory. The [AbstractQueryParser](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/AbstractQueryParser.java) is an abstract class that defines the structure for all concrete parsers, ensuring consistency across various implementations.
        - **Creating the Query Object**: The parser processes the input and creates a [Query](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/Query.java) object. The **`Query`** interface, which [RestaurantQuery](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/RestaurantQuery.java)implements, provides a consistent way to handle various search criteria. It allows different parts of the system to interact with the query without needing to know the specifics, increasing the code's portability and reusability.
        - **Building the Search Predicate**: The [buildPredicate](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/RestaurantDatabase.java#L152-210) method takes a **`Query`** object and constructs a complex **`Predicate`** object based on the user's search criteria. The use of the **`Predicate`** functional interface here allows for the composition of complex logical terms in a concise manner, and it's used to filter the list of restaurants.
        - **Executing the Query**: The predicate is then used in the [executeQuery(query)](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/RestaurantDatabase.java#L96-150) method to filter through the restaurants and find matches. This method can work with any object that implements the **`Query`** interface, demonstrating polymorphism and the principle of "programming to an interface, not an implementation.

### **Custom Features**

### Firebase Integration

### **5. [FB-Auth]**

**Code:** 

[Class “[AuthenticationService](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Util/AuthenticationService.java#L1-78)”, methods“[authenticateUser](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Util/AuthenticationService.java#L36-48)”,”[registerUser](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Util/AuthenticationService.java#L50-62)”,”[getFirebaseUser](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Util/AuthenticationService.java#L63-75)”] **Description of feature:** FB-Auth is an authentication feature utilising Firebase Authentication to manage user sessions within the “Dine Finder” app. It's crucial for functionalities that require user identification, like posting comments, rating restaurants, and engaging in P2P chats. The CommentHistoryAdapter class, which handles the display of user comments, heavily relies on this service to authenticate the user and ensure the correct user data is fetched and displayed.

**Description of implementation:** The AuthenticationService is a singleton class ensuring a consistent point of access for authentication tasks. It encapsulates the FirebaseAuth instance and provides methods for user login, registration, and retrieval of the current user's information.

- The constructor initialises the **`FirebaseAuth`** instance.
- The **`signIn`** method takes a user's email and password, then calls Firebase's **`signInWithEmailAndPassword`** method, returning a task to handle success or failure.
- The **`signUp`** method works similarly but uses **`createUserWithEmailAndPassword`** to register a new user with Firebase.
- The **`getCurrentUser`** method provides a way to fetch the currently authenticated user from Firebase, allowing for user data retrieval or verification of a user's authentication status. This method is essential for features like **`CommentHistoryAdapter`**, which need to fetch user-specific data. It retrieves the current authenticated user from Firebase, enabling the application to pull data related to this user, such as their comments on different restaurants.
- The **`signOut`** method signs out the current user from the app, clearing their authenticated session.

Search-related features

### **6. [Search-Filter]**

Within the “Dine Finder” app, the search filter function is crucial in assisting users in locating the appropriate restaurant.

**Code:**   [FiterDialogManager](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Managers/FilterDialogManager.java),  [MainActivity](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L152-169), [FilterPaser](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/FilterParser.java)

**Description of feature:** Users have the capability to search for restaurants through various filtering criteria, such as based on location, type of restaurant, price range, rating range, and sorting method.

**Implementation Description:**

- Users can opt for the "Near Me" feature, thus leveraging the GPS functionality to filter out nearby restaurants.
- Users can filter based on the type of restaurant.
- Users can filter by price range. They can freely choose both the minimum and maximum prices through a slider. To prevent overlapping drag points, a double slider is utilized for easier operation.
- Users can also filter by rating range. They can freely select both the lowest and highest ratings through a slider. Just like with price, to avoid overlapping, a double slider is incorporated.
- Multiple sorting options are available for users, such as sorting by price from low to high or high to low, or by popularity based on ratings.
- All UI components on the interface are managed and initialized through the `FilterDialogManager` class.
- All filter values are stored within the `FilterValues` inner class.
- The `FilterParser` class is predominantly used to parse the selected filtering criteria from users and constructs a `RestaurantFilterQuery` object based on these criteria, representing a restaurant filter query.
- The setupFilterDialogManager() method in MainActivity sets up the filter dialog manager, its associated button, and the behavior when filter values change.
- When users alter filtering criteria and tap the "Apply" button, the restaurant list updates in accordance with the new filtering criteria.
- All elements on the interface, such as checkboxes, dropdowns, and sliders, are mapped to their respective filtering criteria.
- An `AlertDialog` is utilized to craft and display the filter dialog.
- To enhance the user experience, a "Reset" button has been added, enabling users to swiftly clear all filtering criteria.
- A retention feature has been incorporated, ensuring that upon reopening the panel after applying options, the prior selections remain intact.

Greater Data Usage, Handling and Sophistication

### **7. [Data-Profile]**: 
The app's profile page (i.e. navigation side bar) dynamically presents the user's avatar, email address, and username, with the functionality to modify the username, all integrated with Firebase. In the navigation side bar, the user can choose to securely log out of his/her account.

**Code:**

[Class “[NavigationManager](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Managers/NavigationManager.java)”, method “[initNavigationView](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Managers/NavigationManager.java#L43-98)”] 

[Class”[MainActivity](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java)”, method “[logoutMenu](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L424-469)”]

**Description of feature:** 

- Displays the user's profile picture, email, and editable username on the profile page (i.e. side navigation bar).
- Synchronises user data with Firebase, ensuring real-time updates and persistent data storage.
- Executes user logout with a confirmation dialog, ensuring an intentional user action for security.

**Description of implementation:** 

- The NavigationManager class's initNavigationView() method initialises the NavigationView with the user's email, profile image, and username fetched from Firebase. It includes listeners for successful data retrieval and username updates.
- The User class encapsulates user information, including unique ID, email, display name, avatar URL, and other relevant data, with appropriate constructors and getters/setters.
- The profile UI layout defined in XML features an EditText field allowing users to view and change their usernames. This field is pre-populated with current data from Firebase and updates the backend upon changes.
- Update the username stored in Firebase by using the `FocusChangeListener` and when the `hasFocus` equals to False, then sync new text from EditText to Firebase as the new username.
- Glide library is used for efficient image loading and display of the user's avatar.
- Exception handling and user feedback are implemented for data retrieval or update failures, enhancing the user experience.
- The logoutMenu method constructs an AlertDialog that seeks user confirmation for logout.

### **8. [Data-GPS]**: 
Use GPS information based on location data in your App. (easy)

Utilisation of GPS data for enhancing user experience by displaying the current city and facilitating nearby dining searches with the “Around Me” filter.

**Code:** 

[Class “[Main Activity](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L276-303)”, methods “[getLatitude](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L276-289)”, “[getLongitude](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L290-304)”] 

[Class “[NavigationManager](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Managers/NavigationManager.java)”, methods “[initNavigationView](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Managers/NavigationManager.java#L101-114)”] 

**Description of feature:** 

- Displays the user’s current city in the side navigation bar, enhancing user orientation and personalisation.
- Activates proximity-based dining options when the user engages the "Around Me" filter, promoting convenience and localised results.

**Description of implementation:** 

- The LocationManager is initialised in MainActivity during the onCreate() lifecycle phase, tapping into system services for location functionalities.
- Latitude and longitude retrieval are handled by getLatitude() and getLongitude() methods, respectively. These functions incorporate permission validation and access to the GPS provider for the device's last recorded location.
- The initNavigationView() method within the NavigationManager class employs the Geocoder class, translating raw GPS coordinates into a human-readable city name. This name is prominently showcased in the application's navigation header, grounding the user in their locale.
- Upon user selection of the "Around Me" filter, the FilterParser class triggers a geocoding process, converting longitude and latitude into a city name and added to the query object for further search or data retrieval operations.

### **9. [Data-Graphical]** 
Create a **Graphical** report viewer to see a report of some useful data from your app. No marks will be awarded if the report is non-graphical. (medium)

**Code:** [Class BarChartFragment, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Fragment/BarChartFragment.java) & [Class LineChartFragment, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Fragment/LineChartFragment.java) & [Class PieChartFragment](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Fragment/PieChartFragment.java), entire file & [Class RadarChartFragment, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Fragment/RadarChartFragment.java) & Class [ViewPagerAdapter](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Adapter/ViewPagerAdapter.java), entire file & [Class Colors,](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Resources/Colors.java) entire file & [ChartsActivity](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/ChartsActivity.java), entire file

Class [RestaurantDatabase](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/RestaurantDatabase.java): method extractData, lines of code: 229 to 306

Class [MainActivity](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java): method initFabButton, lines of code: 323 to 333

**Description of implementation:** 

**Visualization Fragments:**

1. **BarChartFragment:**
    - This fragment visualizes the number of restaurants per city using a bar chart.
    
    **Methods include:**
    
    - **`onCreateView`**: Initializes the fragment view.
    - **`setupBarChart`**: Sets up the bar chart's basic visualization properties.
    - **`getCityRestaurantCounts`**: Fetches restaurant data from a database and counts the number per city.
    - **`populateBarChartData`**: Populates the bar chart data based on the extracted data.
    - **`setupYAxis`**: Configures the Y-axis of the bar chart.
    - **`configureDataSet`**: Sets up properties of the dataset used for the bar chart.
    - **`setupLegend`**: Configures the legend for the bar chart.
2. **LineChartFragment:**
    - Visualizes restaurant data using a line chart.
    
    **Methods include:**
    
    - **`onCreateView`**: Initializes the fragment view.
    - **`extractEntriesAndXValues`**: Extracts data for the line chart from given cost intervals.
    - **`setupLineChart`**: Configures the line chart based on the provided data.
    - **`configureXAxis`**: Configures the X-axis of the line chart.
    - **`configureLeftYAxis`**: Configures the left Y-axis of the line chart.
    - **`configureAndSetData`**: Sets up and applies the dataset properties to the line chart.

3. **PieChartFragment:**

- Visualizes the distribution of restaurant types using a pie chart.

    **Methods include:**

- **`onCreateView`**: Initializes the fragment view.
- **`processRestaurantData`**: Processes restaurant data to produce pie chart entries.
- **`setupPieChart`**: Configures the pie chart based on the provided entries.
- **`configurePieChartLegend`**: Configures the pie chart's legend.

4. **RadarChartFragment:**

- Visualizes the distribution of restaurant ratings using a radar chart.

    **Methods include:**

- **`onCreateView`**: Initializes the fragment view.
- **`extractRadarData`**: Fetches rating data from a database and creates radar chart entries.
- **`setupRadarChart`**: Sets up the radar chart based on the extracted data.
- **`setupRadarChartXAxis`**: Configures the X-axis of the radar chart.

**ViewPager Adapter:**



- A custom adapter for ViewPager2 that supports BarChart, LineChart, PieChart, and RadarChart fragments.

**Methods include:**

- **`createFragment`**: Provides the appropriate fragment based on a given position.
- **`getItemCount`**: Returns the number of fragments.

**Main Activity:**

**ChartsActivity:**

- This activity displays various charts using a combination of TabLayout and ViewPager2.

**Methods include:**

- **`onCreate`**: Initializes the activity.
- **`setupUIComponents`**: Initializes UI components.
- **`setupListeners`**: Sets up interactions between the TabLayout and ViewPager2.
- **`initFabButton`**: Initializes the floating action button for navigation.

**Utility Class:**

**Colors:** A utility class that offers a collection of color constants for consistent use across the application.

In summary, this code provides a suite of chart visualization tools using fragments. These fragments are hosted within the **`ChartsActivity`** that uses a **`ViewPager2`** and **`TabLayout`** for navigation. Each chart fragment visualizes different facets of restaurant data like distribution by city, type, or ratings. The **`Colors`** utility ensures a cohesive color palette throughout the app. 

Peer to Peer Messaging

### **10. [P2P-DM]**
Provide users with the ability to message each other directly in private. (hard)
- Code: [RecentChatsActivity.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/RecentChatsActivity.java), [P2PChatActivity.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/P2PChatActivity.java), [BackGroundService](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/tree/main/src/app/src/main/java/G26/Project/ViewController/BackGroundService), [MessageAdapter.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Adapter/MessageAdapter.java), [ChatAdapter.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Adapter/ChatAdapter.java), [Chat.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/P2P/Chat.java), [Message.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/P2P/Message.java), [FireStoreService.java#L157-203](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Util/FireStoreService.java#L157-203)
- **Description of Feature:** The "P2P-DM" feature empowers users within our app to send direct, private messages to one another. This functionality is crucial for enabling personal communication between users, thereby enhancing community engagement and user interactivity within the app.
- **Description of Our Implementation**:
    - **Firebase Integration for Message Handling**:
    We leverage Firebase's robust infrastructure as the backbone for our messaging system. By utilizing Firebase as a message relay server, combined with its real-time listening capabilities, we ensure the immediate delivery of new messages to the appropriate recipients. This system relies on the unique IDs assigned to users upon registration with Firebase.
    - **Unique Chat Identifiers**:
    A dedicated '[chatMessages](https://console.firebase.google.com/project/gourmet-restaurant-search/firestore/data/~2FchatMessages~2FMG1dZMZYu7PV5teMT9hYMpbmcsr2_ReaUwKFGwMblJUcCmIlreYpFT412?hl=zh-cn)' collection within Firebase stores all user messages. Unique chat IDs are generated by concatenating the IDs of two communicating users, sorted alphabetically to maintain consistency and prevent duplication. Click [here](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Util/FirebaseUtil.java#L16-25) to see how we generate the chatID. These unique chat IDs direct users to the '[message](https://console.firebase.google.com/project/gourmet-restaurant-search/firestore/data/~2FchatMessages~2FMG1dZMZYu7PV5teMT9hYMpbmcsr2_ReaUwKFGwMblJUcCmIlreYpFT412~2Fmessages?hl=zh-cn)' sub-collection, where individual messages are stored chronologically.
    - **Message Transmission Protocol**:
    When a user intends to send a message, they must specify a valid recipient email address. The system first [verifies](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/RecentChatsActivity.java#L127-173) the existence of this user within the '[users](https://console.firebase.google.com/project/gourmet-restaurant-search/firestore/data/~2FUsers~2FMG1dZMZYu7PV5teMT9hYMpbmcsr2?hl=zh-cn)' collection. If the user exists, their ID is retrieved; otherwise, an error is flagged, indicating the user's non-existence.
        - Upon successful validation, the message is composed and stored within the '[message](https://console.firebase.google.com/project/gourmet-restaurant-search/firestore/data/~2FchatMessages~2FMG1dZMZYu7PV5teMT9hYMpbmcsr2_ReaUwKFGwMblJUcCmIlreYpFT412~2Fmessages?hl=zh-cn)' sub-collection specific to the unique chat ID shared between the sender and receiver, the method is [here](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Util/FireStoreService.java#L150-203).
        - For message reception, users have listeners attached to their unique chat segments. When a new message is posted under their chat ID, the listener triggers, and the message data is loaded for display (see **FB-Persist Extension** part below for more details).
    - **Data Structure for Messages**:
    Messages are stored with the following structure, ensuring a uniform template for all communications within the system:
        - **`chatId`**: A unique identifier for the conversation.
        - **`content`**: The actual text content of the message.
        - **`senderUid`**: The unique user ID of the sender.
        - **`timestamp`**: The time the message was sent, aiding in chronological sorting.

### Firebase Integration

### **11. [FB-Persist]** 
Use Firebase to persist all data used in your app. (medium)

- **Code:** [MainActivity.java#L394-422](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L394-422), [FireStoreService.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Util/FireStoreService.java)
- **Description of Feature:** The "FB-Persist" feature is essential for maintaining a consistent and real-time user experience across our app, ensuring that all interactions and data are not only stored locally but also persist in the cloud database. This aspect is crucial for features like live ratings, reviews, and peer-to-peer (P2P) messaging.
- **Description of our Implementation**: Our application operates on a client-server architecture, with Firestore serving as the backbone for all data storage needs. Upon a user's login, relevant data, particularly restaurant information, is fetched from Firestore and loaded into local memory, leveraging a nested B-tree structure for efficient access. All new user data is then store in firebase when generated.
- **Real-time Data Persist for Ratings and P2P Messages**: A critical part of our app's functionality is the real-time persistence of data, particularly for restaurant reviews and user messages. Here's how we handle each:
    - **Restaurant Ratings**: Ratings are intricately linked to their respective restaurants, stored within [Firestore](https://console.firebase.google.com/project/gourmet-restaurant-search/firestore/data/~2Frestaurants~2F007bXrnNE0wkWLQLT39s?hl=zh-cn) following the hierarchy: **`restaurants collection -> specific restaurant ID -> comments (as a sub collection)`**. Each rating document houses data such as:
        - **`rating`**: a numeric value representing user satisfaction.
        - **`text`**: the actual rating content, a string.
        - **`timestamp`**: the exact time the review was posted, crucial for real-time updates.
        - **`userId`** and **`userName`**: strings for user identification.
    
    When users post ratings, this information is immediately [sent](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Util/FireStoreService.java#L94-148) to Firestore, ensuring data consistency across all instances of the app. We also [store](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Util/FireStoreService.java#L205-231) the rating history of a curtain usr in the sub collection ‘[commentHistory](https://console.firebase.google.com/project/gourmet-restaurant-search/firestore/data/~2FUsers~2Fbs19gMp9iearI6VXf6TgsYk7BhI2~2FcommentHistory?hl=zh-cn)’, which is used to store the followed item within groups.
    
    - **P2P Messages**: User messages follow a different approach. They are stored in a [chatMessages](https://console.firebase.google.com/project/gourmet-restaurant-search/firestore/data/~2FchatMessages~2FMG1dZMZYu7PV5teMT9hYMpbmcsr2_ReaUwKFGwMblJUcCmIlreYpFT412?hl=zh-cn) collection, with each message's ID being a concatenation of the participating users' IDs. This ID formation ensures a unique identifier for every conversation. Each message document contains:
        - **`chatId`**: a unique string identifier for the conversation.
        - **`content`**: the message text, a string.
        - **`senderUid`**: the sender's user ID, a string.
        - **`timestamp`**: numeric value, pinpointing the message time.
    
    Upon the sending of a message, these details are [uploaded](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Util/FireStoreService.java#L149-203) to Firestore, aligning with our real-time data persist goal.
    

**[FB-Persist Extension: synchronous]** Without restarting, the app should be updated synchronously as the remote database (Firebase) is updated. This means that users will be able to see the instant updates from another user/content provider. (hard)

- **Code:** [MessageListenerService.java](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/BackGroundService/MessageListenerService.java), [MainActivity](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L390-422), [RestaurantAdapter](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Adapter/RestaurantAdapter.java#L93-126), [RecentChatsActivity](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/RecentChatsActivity.java#L196-282), [P2P_Activity](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/P2PChatActivity.java#L55-68)
- **Description of Feature:** The extension of the "FB-Persist" feature ensures that our app maintains synchronous updates with the Firebase database without requiring a restart. This mechanism is crucial for displaying instant updates to the users, thereby enhancing real-time interactivity and ensuring that the data remains fresh and relevant.
- **Description of Our Implementation**:
    - **Real-time Data Listeners**: Abandoning the traditional request-response model, our app implements Firestore's real-time data listeners for instant data synchronization. This approach involves setting up listeners at various data points, ensuring that any change in the Firestore database triggers an immediate update within the app.
    - **Real-time Data Listeners for Restaurant Data:**
        - When the app is launched, it initiates a connection with Firebase and establishes [real-time listeners](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/MainActivity.java#L390-422) on the restaurant collection.
        - These listeners are active and waiting for any event that signifies a change in the database, specifically looking for **`addition`** or **`modification`** actions.
            - If a new restaurant is added (signified by a new document in the ‘[restaurants](https://console.firebase.google.com/project/gourmet-restaurant-search/firestore/data/~2Frestaurants~2F007bXrnNE0wkWLQLT39s?hl=zh-cn)’ collection), the listener triggers the **`insertRestaurant`** method. This method performs two actions: it updates the local database (in-memory B-tree structure) by [inserting](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/RestaurantDatabase.java#L60-75) the new restaurant's data, and it calls [restaurantAdapter.addData](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Adapter/RestaurantAdapter.java#L93-102), which updates the RecyclerView's dataset and notifies the UI to refresh the list, showing the new restaurant.
            - If there's a modification in any of the existing restaurants, e.g., a new rating, the listener identifies the change and executes the [updateRestaurant](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/RestaurantDatabase.java#L77-88) and [updateDataForItem](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Adapter/RestaurantAdapter.java#L104-126) method. The two methods updates the local database and refreshes the UI with the new data, and the local update is supported by B-tree [update](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/BTree.java#L216-235) operation. Additionally, it creates a new Intent carrying the updated restaurant's data, broadcasts it locally within the app. The broadcast receiver in **`RestaurantDetailActivity`** catches this broadcast, retrieves the updated restaurant data from the Intent, and r[efreshes](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/RestaurantDetailActivity.java#L87-102) the UI elements in the detail page accordingly.
    - **Enhanced Real-time P2P Messaging:**
        - **How we implement:**
            - Every user in the app has an associated '[chats](https://console.firebase.google.com/project/gourmet-restaurant-search/firestore/data/~2FUsers~2FMG1dZMZYu7PV5teMT9hYMpbmcsr2?hl=zh-cn)' map in their unique Firestore document. This map contains chat IDs, which are unique identifiers [created](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/Util/FirebaseUtil.java#L16-25) by concatenating the user IDs of the two chat participants. Each chat ID is associated with a 'lastUpdated' timestamp.
            - When a user sends a message, the app updates the 'lastUpdated' timestamp in the 'chats' map for both the sender and receiver. If this is their first chat, a new chat ID is generated and added to the map.
            - [MessageListenerService](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/BackGroundService/MessageListenerService.java) is a background service started when the app launches. It performs two main tasks:
                - It initially loads the current user's chat timestamps from Firestore into a local [Map<String, Long>](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/BackGroundService/MessageListenerService.java#L47-71) structure. This map keeps track of the last time each chat was updated and is used for comparison purposes when new messages arrive.
                - It sets up a [listener](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/BackGroundService/MessageListenerService.java#L72-100) on Firestore at the location where chat messages are stored. This listener is specifically looking for changes in the 'lastUpdated' timestamps within the user's '[chats](https://console.firebase.google.com/project/gourmet-restaurant-search/firestore/data/~2FUsers~2FMG1dZMZYu7PV5teMT9hYMpbmcsr2?hl=zh-cn)' map.
            - When the listener detects a change (a new message), it compares the new timestamp with the one stored in [lastUpdatedTimestamps](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/ViewController/BackGroundService/MessageListenerService.java#L34). If there's a discrepancy (indicating a new chat or new message), it triggers a local broadcast within the app. This broadcast contains the new message's content and the sender's UID.
            - **`RecentChatsActivity`** and **`P2PChatActivity`** both contain broadcast receivers listening for these message broadcasts. When they receive a broadcast, they extract the message data from the Intent and use it to update the UI:
                - **`RecentChatsActivity`** calls **`updateRecentChats()`**, which refreshes the list of chats to include the new message.
                - **`P2PChatActivity`** calls **`updateChatUI(newMessage)`**, which inserts the new message into the current chat view.
        - **Why our approach works:**
            1. **P2P Model with Firebase:**
                
                We use a peer-to-peer model by reading and writing message data to Firebase. Here, Firebase acts as the medium that stores and facilitates the message exchange, allowing users to essentially communicate "directly" with each other through Firebase.
                
            2. **Understanding Message Relevance:**
                
                For a user to receive a message, they need to know the target user's ID to determine if a message is relevant to them. If the app were designed naively, it would scan through the entire chat database to find relevant messages each time.
                
            3. **Efficiency Concerns:**
                
                Listening to or traversing the entire [chatMessages](https://console.firebase.google.com/project/gourmet-restaurant-search/firestore/data/~2FchatMessages?hl=zh-cn) database constantly is not efficient. Imagine there are 10,000 users. If each user's client was listening to the entire chat database, the bandwidth consumption would skyrocket, leading to:
                
                - Slower response times.
                - Higher costs associated with Firebase usage.
                - Increased chances of hitting Firebase's rate limits or quotas.
            4. **Clever Design Choice:**
                
                To avoid these pitfalls, a more strategic approach is adopted:
                
                - The app listens **only** to the [chats](https://console.firebase.google.com/project/gourmet-restaurant-search/firestore/data/~2FUsers~2FMG1dZMZYu7PV5teMT9hYMpbmcsr2?hl=zh-cn) field of the currently logged-in user.
                - The app stores this data locally during initialization. Thus, subsequent operations can compare local data with updated data on Firebase.
                - Upon detecting a change (like a new message), the app can identify the sender of the message by comparing the local and cloud data.
            5. **Benefits of This Approach:**
                - It reduces the amount of data being constantly fetched. Instead of downloading all messages every time there's a change, the app identifies which specific message is new.
                - Messages are sorted by timeline, which means the system is optimized to fetch only the most recent messages. Older, less relevant messages stay out of the immediate data-fetching process, saving on resources.
    
    In conclusion, the real-time synchronization feature ensures that users have the most current data right at their fingertips, enhancing their overall experience and satisfaction with the app.
    

### User Interactivity

12. [Interact-Micro] 
   The ability to micro-interact with items/users (e.g. like, block, connect to another user, etc.) [stored in-memory]. (easy)
    - Code: Class Restaurant, entire file & RatingDialogFragment, entire file & RatingAdapter, entire file
    - Class RestaurantDetailActivity: method setupViews, lines of codes: 109-126
    - Description of your implementation:
        1. **Restaurant Details Activity**:
            - The **`setupViews()`** method initializes the user interface components for the restaurant details screen. It sets the content view and initializes a **`RecyclerView`** for displaying restaurant ratings.
            - Ratings are fetched and displayed in this **`RecyclerView`** using the **`RatingAdapter`**.
            - Each restaurant has an ID (**`restaurantId`**), fetched from the intent extras, which is used to identify the particular restaurant.
            - A floating action button (FAB) is also present in the view. When this FAB is clicked, the **`showRatingDialog()`** method is invoked, though the code for this method isn't provided. It likely displays the **`RatingDialogFragment`** for the user to submit a new rating and review.
        2. **Restaurant Class**:
            - Represents a restaurant with various attributes like name, city, type, image, cost, rating count, mean rating, and an ID.
            - Contains getter and setter methods for its attributes.
            - Implements the **`Comparable`** interface to compare two restaurants based on their cost, which can be useful for sorting.
        3. **RatingDialogFragment**:
            - Represents a dialog where users can submit ratings and reviews for a restaurant.
            - Contains a **`RatingBar`** to input a numeric rating and an **`EditText`** to input a review.
            - Once the user selects the rating and writes a review, they can submit the data. The data is then saved to the database (Firebase Firestore in this case) using the **`FireStoreService`**. The method **`saveRatingToDatabase`** saves the rating, and the method **`storeCommentInFirebase`** stores the comment along with other restaurant details and the timestamp.
            - If the user decides not to submit, they can cancel the dialog.
        4. **RatingAdapter**:
            - An adapter class responsible for populating a **`RecyclerView`** with ratings.
            - The adapter uses a list of **`Rating`** objects. Each **`Rating`** object represents a single rating entry (this object isn't fully detailed in the provided code, but it seems to contain user name, rating value, review text, and timestamp).
            - The **`onBindViewHolder`** method populates each item in the **`RecyclerView`** with the details of a rating. This includes displaying the user's name, their rating, the review text, and the date of the rating.
            - The **`updateRatings`** method allows updating the list of ratings displayed in the **`RecyclerView`** with a new list of ratings.
        5. **Firestore Integration**:
            - The Firebase Firestore is being used for backend data storage.
        6. **Memory Management and UI Refreshing**:
            - **RecyclerView and Adapter Implementation**: We implemented a **`RecyclerView`** for displaying data due to its efficiency, especially when dealing with large datasets. It works by only loading the items that are currently visible on the screen into memory, significantly reducing memory usage. As users scroll through the list, the **`RecyclerView`** recycles and reuses the views to maintain optimal performance.
            - **RatingDialogFragment's Memory Release**: Once users have entered their ratings and reviews, we ensure that the dialog is dismissed using the **`dismiss()`** method. This action is vital as it releases the memory resources taken up by the dialog, allowing for optimized memory management.
            - **Activity Lifecycle Management**: In our implementation, we have adhered to the Android activity lifecycle. This ensures that resources are used efficiently. When components like Activities or Fragments end their lifecycle, they release the memory they had been using, keeping memory consumption balanced and helping prevent any potential lag or slowdowns.
        
        In our implementation, users can view restaurant details, including ratings, and provide new ratings and reviews for a restaurant. These reviews are promptly saved to Firebase Firestore. Subsequently, they are displayed in the restaurant details using a **`RecyclerView`**. Concurrently, we have emphasized efficient memory management throughout our application. By deploying tools like **`RecyclerView`**, ensuring judicious resource management in the **`RatingDialogFragment`**, and diligently adhering to the Android activity lifecycle, our objective remains clear: to deliver a seamless and fluid user experience.
        
    
13. [Interact-Follow] 
    The ability to ‘follow’ items. There must be a section that presents all the items followed by a user, grouped and ordered. [stored in-memory] (medium)
    - Code: Class CommentModel, entire file & Class Rating, entire file & Class CommentHistoryActivity, entire file & Class CommentHistoryAdapter, entire file
    - Description of your implementation:
        1. **CommentModel Class**:
            - This class represents a user's comment or review for a restaurant.
            - **`CommentModel`** extends the **`Rating`** class, meaning it inherits properties and methods of the **`Rating`** class.
            - The comment contains details about the restaurant (**`restaurantName`**, **`restaurantType`**, **`restaurantCity`**), the user's comment timestamp (**`userDate`**), and also inherits fields from the **`Rating`** class such as **`userId`**, **`userName`**, **`rating`**, **`text`**, and **`timestamp`**.
        2. **Rating Class**:
            - Represents a user's rating and review for a restaurant.
            - Contains fields for **`userId`**, **`userName`**, **`rating`**, **`text`**, and **`timestamp`** (the time the rating was given).
            - The **`Rating`** class has constructors that either accept a Firebase user or custom user details.
        3. **CommentHistoryActivity Class**:
            - Displays a user's comment history on various restaurants.
            - The activity fetches user comments from the Firebase Firestore and displays them in a **`RecyclerView`**.
            - The **`loadCommentsFromFirebase`** method is used to fetch the user's comments from Firestore, ordered by the comment date in descending order.
            - The **`setupRecyclerView`** method initializes and sets up the **`RecyclerView`** to display the fetched comments.
        4. **CommentHistoryAdapter Class**:
            - An adapter for the **`RecyclerView`** that handles displaying each user comment.
            - Contains a list of **`CommentModel`** objects, which represent individual comments.
            - The **`onBindViewHolder`** method binds the data from a **`CommentModel`** object to a **`CommentViewHolder`**. This includes displaying the restaurant's name, type, city, and also creating a sub-recycler view for ratings.
            - The **`updateComments`** method allows updating the list of comments and notifies the **`RecyclerView`** that the data has changed.
        5. **CommentHistoryAdapter.CommentViewHolder Class**:
            - A **`ViewHolder`** class within the adapter that represents a single item in the comment history.
            - Contains **`TextView`**s for the restaurant's name, type, city, and a **`RecyclerView`** for ratings.
        
        **Narrative in Relation to the Requirements**:
        The system's ability to follow and display items can be deduced from the **`CommentHistoryActivity`** class. Here, users can view their comment history, which is stored and fetched from Firebase Firestore. The comments are displayed in a **`RecyclerView`**, which means they are grouped based on the restaurant thy comment, and ordered based on the **`userDate`** (in descending order), which satisfies the requirement of presenting all the items followed by a user in a grouped and ordered fashion. The comments, in this case, act as the "followed" items.
        

---

## **Summary of Known Errors and Bugs**

- P2P UI Update Bug:
    
    In the P2P interface, if two users send multiple messages to each other in a very short time interval, the UI of the chat interface may lag behind due to network latency, and you need to re-tap the current conversation to update the UI.
    
- GPS
    
    When the device doesn't get the GPS functionality correctly, we show ‘Unknown’ on the UI and then we can't search for the field ‘around me’ in the filter
    

## **Testing Summary**

### Test type:

JUnit Test

### Number of test cases:

110

### Code coverage:

81%

### Test result screenshot:

![App Test Result](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/raw/main/items/App_test_result.png)

`FilterParser`, `QueryParserFactory`, `RestaurantFilterQuery` and `RestaurantSearchFacade` are not tested in JUnit test, because these four classes need to use Firebase.

1. **Tests for Data:**
    
    **Test Class: BTreeTest**
    
    - **Code**:
    [BTreeTest Class, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/test/java/G26/Project/Model/Data/BTreeTest.java) for the [BTree Class, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/BTree.java)
    - **Number of test cases:** 5
    - **Code coverage:** 89%
    - **Types of tests created and descriptions**:
        1. **Insert and Search Test (testInsertAndSearch)**: Validates that the **`BTree`** can correctly insert multiple keys and subsequently search for them based on a given predicate. It also tests searching for non-existent keys.
        2. **Insert and Update Test (testInsertAndUpdate)**: Tests the ability of the **`BTree`** to correctly insert keys and update an existing key.
        3. **Insert and Update Non-Existent Test (testInsertAndUpdateNonExistent)**: Validates the **`BTree`**'s behavior when attempting to update a non-existent key, ensuring the structure remains unchanged.
        4. **To List Conversion Test (testToList)**: Assesses the **`BTree`**'s capability to be converted into a sorted list and verifies the order of the elements.
    
    **Test Class: RestaurantDatabaseTest**
    
    - **Code:** 
    [RestaurantDatabaseTest Class, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/test/java/G26/Project/Model/Data/RestaurantDatabaseTest.java) for the [RestaurantDatabase Class, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Data/RestaurantDatabase.java)
    - ***Number of test cases:*** 10
    - ***Code coverage:** 79%*
    - ***Types of tests created and descriptions:***
        1. **Singleton Pattern Test (getInstance)**: Ensures that the **`RestaurantDatabase`** class correctly implements the singleton pattern.
        2. **Execute Query Test (executeQuery)**: Tests executing a query against the database and checks if the expected results are returned.
        3. **No Matches Query Test (queryNoMatches)**: Ensures the database returns an empty list when the query doesn't match any restaurant.
        4. **Insertion Test (insertRestaurant)**: Checks if a restaurant is correctly inserted into the database.
        5. **Update Test (updateRestaurant)**: Tests updating a restaurant in the database and checks if the old restaurant record still exists after an update.
        6. **Multiple Conditions Search Test (multipleConditionsSearch)**: Ensures the database returns correct results when multiple conditions are specified in a query.
        7. **Convert to List Test (toListTest)**: Tests the method for converting the database's contents into a list.
        8. **Data Extraction Test (extractDataTest)**: Tests extracting certain fields of data from the database and ensures the correct values are present.

1. **Tests for P2P**
    
    **Test Class: ChatTest**
    
    - **Code:**
    [ChatTest Class, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/test/java/G26/Project/Model/P2P/ChatTest.java) for the [Chat Class, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/P2P/Chat.java)
    - ***Number of test cases:*** 4
    - ***Code coverage:*** 100%
    - ***Types of tests created and descriptions:***
        1. **Username Getter & Setter Test (getAndSetUserName)**: Tests the getter and setter methods for the **`username`** property of the **`Chat`** class. Ensures that the set value matches the retrieved value.
        2. **Last Message Getter & Setter Test (getAndSetLastMessage)**: Tests the getter and setter methods for the **`lastMessage`** property of the **`Chat`** class. Confirms that the set message matches the retrieved message.
        3. **Timestamp Getter & Setter Test (getAndSetTimestamp)**: Tests the getter and setter methods for the **`timestamp`** property of the **`Chat`** class. Validates that the set timestamp corresponds with the retrieved timestamp.
        4. **Target User UID Getter & Setter Test (getAndSetTargetUserUid)**: Tests the getter and setter methods for the **`targetUserUid`** property of the **`Chat`** class. Checks that the set target user UID matches the retrieved UID.
    
    **Test Class: MessageTest**
    
    - **Code**:
    [MessageTest Class, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/test/java/G26/Project/Model/P2P/MessageTest.java) for the [Message Class, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/P2P/Message.java)
    - **Number of test cases**: 11
    - **Code coverage**: 84%
    - **Types of tests created and descriptions**:
        1. **Message Content Retrieval Test (getMessageContent)**: Validates that the message content retrieved matches the initial value ("Hello, Test!").
        2. **Message ID Setting Test (setMessageId)**: Sets a new message ID and confirms that it matches the expected value.
        3. **Sender UID Setting Test (setSenderUid)**: Updates the sender UID and verifies that the updated value is as expected.
        4. **Receiver UID Setting Test (setReceiverUid)**: Changes the receiver UID and checks that the new value matches the expected one.
        5. **Message Content Update Test (setMessageContent)**: Modifies the message content and ensures that the updated content is retrieved correctly.
        6. **Timestamp Setting Test (setTimestamp)**: Sets a new timestamp for the message and verifies that the retrieved timestamp matches the new value.
        7. **Message ID Retrieval When Unset Test (getMessageId)**: Ensures that the message ID is **`null`** when it hasn't been initialized.
        8. **Sender UID Retrieval Test (getSenderUid)**: Validates that the sender UID matches the initially set value.
        9. **Receiver UID Retrieval Test (getReceiverUid)**: Confirms that the receiver UID retrieved matches the initial value.
        10. **Timestamp Retrieval Test (getTimestamp):** Verifies that the retrieved timestamp matches the initially set value.

1. **Tests for Restaurant**
    
    **Test Class: RestaurantTypeTest**
    
    - **Code:**
        
        [RestaurantTypeTest Class, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/test/java/G26/Project/Model/Restaurant/RestaurantTypeTest.java) for the [RestaurantType Class, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Restaurant/RestaurantType.java)
        
    - **Number of test cases:** 11
    - **Code coverage:** 100%
    - **Types of tests created and descriptions:**
        1. **String to Enum Mapping Test (fromString)**: Checks if the method correctly maps a string to its corresponding enum.
        2. **String to Enum Exception Test (fromString with invalid input)**: Expects an exception when trying to map an invalid string to its enum.
        3. **All Enum Values Test (values)**: Ensures all enum values are returned correctly.
        4. **String to Enum Using ValueOf Test (valueOf)**: Validates correct mapping from string to enum using the **`valueOf`** method.
        5. **ValueOf Exception Test (valueOf with invalid input)**: Expects an exception when trying to map an invalid string using **`valueOf`**.
        6. **Enum to String Conversion Test (toString)**: Ensures the **`toString`** method returns the correct enum name.
        7. **Enum Name Retrieval Test (name)**: Validates the name of the enum is returned correctly.
        8. **Ordinal Value Test (ordinal)**: Checks the correct ordinal value for each enum.
        9. **Enum Comparison Test (compareTo)**: Compares two enums and ensures correct results.
        10. **Enum Declaring Class Test (getDeclaringClass)**: Ensures the correct class object is returned.
        11. **Enum Element Consistency Test**: Tests if each enum element correctly matches its string representation.
    
    **Test Class: UserTest**
    
    - **Code:**
        
        [UserTest Class, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/test/java/G26/Project/Model/Restaurant/UserTest.java) for the [User Class, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Restaurant/User.java)
        
    - **Number of test cases:** 8
    - **Code coverage:** 100%
    - **Types of tests created and descriptions:**
        1. **UID Retrieval Test (getUid)**: Validates if the user's UID matches the expected value.
        2. **Email Retrieval Test (getEmail)**: Checks if the email is correctly retrieved from the user object.
        3. **Message Addition Test (addMessage)**: Ensures a message is correctly added to the user's message list.
        4. **Default Constructor Test**: Validates if the default constructor sets the user's UID and Email to null and if the default profile avatar URL matches the expected default URL.
        5. **Display Name Test (displayNameTest)**: Checks if the display name of the user can be set and retrieved correctly.
        6. **Profile Avatar URL Test (profileAvatarTest)**: Tests the setting and retrieval of the user's profile avatar URL.
        7. **Multiple Messages Addition Test (addMultipleMessagesTest)**: Checks if multiple messages can be added to the user's message list and retrieved correctly.
        8. **Profile Avatar Default URL Test**: Validates if the default profile avatar URL is correct when using the default constructor.
    
    **Test Class: CommentModelTest**
    
    - **Code**:
    [CommentModelTest Class, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/test/java/G26/Project/Model/Restaurant/CommentModelTest.java) for the [CommentModel Class, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Restaurant/CommentModel.java)
    - **Number of test cases:** 14
    - **Code coverage:** 83%
    - **Types of tests created and descriptions**:
        1. **Full Parameter Constructor Test (testCommentModelWithAllParameters)**: Ensures that the **`CommentModel`** is correctly instantiated with all provided parameters and their values are retrievable.
        2. **Restaurant Name Getter Test (getRestaurantName)**: Validates that the stored restaurant name is correctly retrieved.
        3. **Restaurant Name Setter Test (setRestaurantName)**: Tests the ability to modify and subsequently retrieve the restaurant's name.
        4. **Restaurant Type Getter Test (getRestaurantType)**: Confirms that the set restaurant type is accurately retrieved.
        5. **Restaurant Type Setter Test (setRestaurantType)**: Checks the capability to change and retrieve the restaurant's type.
        6. **Restaurant City Getter Test (getRestaurantCity)**: Validates the correct retrieval of the stored restaurant city.
        7. **Restaurant City Setter Test (setRestaurantCity)**: Tests the ability to change and subsequently fetch the restaurant's city.
        8. **User Name Getter Test (getUserName)**: Confirms that the set user's name is accurately retrieved.
        9. **User Name Setter Test (setUserName)**: Checks the capacity to modify and retrieve the user's name.
        10. **User Date Getter Test (getUserDate)**: Validates the retrieval of the stored user date.
        11. **User Date Setter Test (setUserDate)**: Tests the ability to modify and subsequently fetch the user's date.
        12. **Initialization with Null Values Test (testNullValues)**: Validates that the **`CommentModel`** can be correctly initialized with **`null`** values and these null values can be retrieved accurately.
    
    **Test Class: RatingTest**
    
    - **Code**:
    [RatingTest Class, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/test/java/G26/Project/Model/Restaurant/RatingTest.java) for the [Rating Class, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Restaurant/Rating.java)
    - **Number of test cases:** 5
    - **Code coverage:** 69%
    - **Types of tests created and descriptions**:
        1. **User ID Getter & Setter Test (getUserIdAndSetUserId)**: Validates that the **`Rating`** object correctly stores and retrieves the specified user ID.
        2. **User Name Getter & Setter Test (getUserNameAndSetUserName)**: Checks the ability to set and subsequently retrieve the user's name in the **`Rating`** object.
        3. **Rating Value Getter & Setter Test (getRatingAndSetRating)**: Ensures that the set rating value is accurately stored and can be retrieved with a small margin of error.
        4. **Review Text Getter & Setter Test (getTextAndSetText)**: Validates the capability to set and fetch the review text for the rating.
        5. **Timestamp Getter & Setter Test (getTimestampAndSetTimestamp)**: Tests the ability to set and subsequently retrieve the timestamp in the **`Rating`** object.
    
    **Test Class: RestaurantTest**
    
    - **Code**:
    [RestaurantTest Class, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/test/java/G26/Project/Model/Restaurant/RestaurantTest.java) for the [Restaurant Class, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Restaurant/Restaurant.java)
    - **Number of test cases:** 14
    - **Code coverage:** 100%
    - **Types of tests created and descriptions**:
        1. **Restaurant Name Getter & Setter Test (getAndSetRestaurantName)**: Validates that the **`Restaurant`** object can correctly store and retrieve its name.
        2. **City Getter & Setter Test (getAndSetRestaurantCity)**: Ensures that the **`Restaurant`** object can accurately set and get the city in which it is located.
        3. **Type Getter & Setter Test (getAndSetType)**: Checks the ability of the **`Restaurant`** object to correctly set and fetch the type of restaurant (assumes that the RestaurantType enum has a value called ITALIAN).
        4. **Image URL Getter & Setter Test (getAndSetImage)**: Validates the **`Restaurant`** object's capability to store and retrieve an image URL.
        5. **Cost Getter & Setter Test (getAndSetCost)**: Tests the ability of the **`Restaurant`** object to set and then fetch the cost.
        6. **Cost-based Comparison Test (compareTo)**: Validates the implementation of the **`compareTo`** method by comparing two restaurants with different costs.
        7. **Rating Count Getter & Setter Test (getAndSetRatingCount)**: Validates that the **`Restaurant`** object can correctly store and retrieve the number of ratings it has.
        8. **Mean Rating Getter & Setter Test (getAndSetMeanRating)**: Ensures the **`Restaurant`** object can set and then fetch the mean rating.
        9. **ID Getter & Setter Test (getAndSetRestaurantId)**: Checks that the **`Restaurant`** object can accurately store and then retrieve its ID.
        10. **Cost-based Comparison With Equal Cost Test (compareToWithEqualCost)**: Validates the **`compareTo`** method when comparing two restaurants with the same cost.
        11. **Comparison With Higher Cost Test (compareToWithMoreCost)**: Tests the **`compareTo`** method to ensure it works correctly when the first restaurant has a higher cost than the second.
        12. **Comparison With Lower Cost Test (compareToWithLessCost)**: Ensures the **`compareTo`** method functions properly when the first restaurant has a lower cost than the second.
    
    4. **Test for Search**
        
        **Test Class: AbstractQueryParserTest**
        
        - **Code**: [AbstractQueryParserTest Class](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/test/java/G26/Project/Model/Search/AbstractQueryParserTest.java) entire file for the [AbstractQueryParser Class](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/AbstractQueryParser.java)
        - **Number of test cases**: 5
        - **Code coverage**: 100%
        - **Types of tests created and descriptions**:
        1. **Parse Method Test (parse)**: Tests the **`parse`** method of the **`AbstractQueryParser`**. This test ensures that the parse method returns **`null`** for the given setup.
        2. **Initialize Method Test (initialize)**: Tests the **`initialize`** method of the **`AbstractQueryParser`**. Currently, this test doesn't have any specific behavior to check, and it serves as a placeholder for potential future tests related to the **`initialize`** method.
        3. **DoParsing Method Test (doParsing)**: Validates the behavior of the **`doParsing`** method of the **`AbstractQueryParser`**. Initially, the **`parsedSuccessfully`** flag should be **`false`**. After calling **`doParsing`**, the flag should be set to **`true`**.
        4. **FinalizeParsing Method Test (finalizeParsing)**: Tests the **`finalizeParsing`** method of the **`AbstractQueryParser`**. Ensures that the **`finalizeParsing`** method returns **`null`** for the given setup.
        5. **Setup and Teardown Tests**: While these are not explicit tests, the **`setUp`** and **`tearDown`** methods are part of the test lifecycle. The **`setUp`** method initializes the **`testParser`** with a predefined token list, and the **`tearDown`** method provides a space for cleanup operations post-test, though none are currently implemented.
        
        **Test Class: AttributeConstraintTest**
        
        - **Code**: [AttributeConstraintTest Class](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/test/java/G26/Project/Model/Search/AttributeConstraintTest.java) entire file for the [AttributeConstraint Class](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/AttributeConstraint.java)
        - **Number of test cases**: 5
        - **Code coverage**: 100%
        - **Types of tests created and descriptions**:
        1. **Operator Getter Test (getOperator)**: Tests the getter method for the operator attribute of the **`AttributeConstraint`** class. It checks if the retrieved operator matches the test data.
        2. **Value Getter Test (getValue)**: Tests the getter method for the value attribute of the **`AttributeConstraint`** class. The test ensures that the retrieved value is equal to the test data.
        3. **Equals Method Test (testEquals)**: Comprehensive test for the **`equals`** method of the **`AttributeConstraint`** class. This test covers various scenarios:
            - Identity comparison: Checks that the same object is considered equal to itself.
            - Null value comparison: Ensures that the object is not equal to **`null`**.
            - Different class comparison: Validates that the object is not equal to an instance of a different class.
            - Logical equality comparison: Tests that two logically equal **`AttributeConstraint`** objects are considered equal.
            - Logical inequality comparison: Checks that two logically different **`AttributeConstraint`** objects are not equal.
        4. **HashCode Method Test (testHashCode)**: Tests the **`hashCode`** method of the **`AttributeConstraint`** class. The following scenarios are covered:
            - Two logically equal **`AttributeConstraint`** objects must return the same hash code.
            - Two logically different **`AttributeConstraint`** objects should return different hash codes.
        5. **ToString Method Test (testToString)**: Validates the **`toString`** method of the **`AttributeConstraint`** class. This test ensures that the string representation of the object matches the expected format and content.
        6. **Setup and Teardown Tests**: While these are not explicit tests, the **`setUp`** and **`tearDown`** methods are part of the test lifecycle. The **`setUp`** method initializes the **`AttributeConstraint`** object with test data, and the **`tearDown`** method sets the **`AttributeConstraint`** object to **`null`** to free up resources.
        
        **Test Class: BasicQueryParserTest**
        
        - **Code:** [BasicQueryParserTest Class, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/test/java/G26/Project/Model/Search/BasicQueryParserTest.java) for the [BasicQueryParser Class](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/BasicQueryParser.java)
        - **Number of test cases:** 4
        - **Code coverage:** 89%
        - **Types of tests created and descriptions:**
            1. **Basic Parsing Test (testBasicParsing)**: Validates the ability of the parser to correctly transform a basic query containing city and restaurant name information into a structured **`RestaurantQuery`** object. This ensures that the parser can handle straightforward queries.
            2. **Multiple Cities Parsing Test (testMultipleCitiesParsing)**: Ensures that the parser can correctly handle and structure queries that include multiple cities. This is crucial for users who want to search restaurants across different cities.
            3. **Multiple Restaurant Names Parsing Test (testMultipleRestaurantNamesParsing)**: Validates the parser's capability to handle queries with multiple restaurant names. It verifies that the parser can differentiate and structure distinct restaurant names within a single query string.
            4. **Mixed Attributes Parsing Test (testMixedAttributesParsing)**: Evaluates the parser's ability to process queries containing a mix of attributes like city, cost, rating, and type. This ensures the robustness of the parser in handling diverse query elements.
            
            **Test Class: PairTest**
            
            - **Code:** [PairTest Class, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/test/java/G26/Project/Model/Search/PairTest.java) for the [Pair Class, entire file](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/Pair.java)
            - **Number of test cases:** 3
            - **Code coverage:** 100%
            - **Types of tests created and descriptions:**
                1. **Key Retrieval Test (getKey)**: Validates if the key of the pair matches the expected key value.
                2. **Value Retrieval Test (getValue)**: Checks if the value of the pair is correctly retrieved and matches the expected value.
                3. **ToString Method Test (testToString)**: Ensures that the **`toString`** method of the pair returns the expected formatted string representation of the key-value pair.
                
            
            **Test Class: QueryTokenizerTest**
            
            - **Code:** [QueryTokenizerTest Class](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/test/java/G26/Project/Model/Search/QueryTokenizerTest.java), entire file for the [QueryTokenizer Class](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/QueryTokenizer.java), entire file.
            - **Number of test cases:** 2
            - **Code coverage:** 100%
            - **Types of tests created and descriptions:**
                1. **Tokenize Method Test (testTokenize)**: Validates the **`tokenize`** method of the QueryTokenizer class. Given a sample query string, this test ensures the correct number of tokens is generated. It also verifies each token and its type to ensure the tokenization process is correct.
                2. **Debug Tokenize Method Test (debugTokenize)**: This is a debugging test case that tokenizes a sample query and prints each token and its type. While this method does not assert any conditions, it provides insight into the tokenization process and can be used for manual verification.
                
            
            **Test Class: RestaurantQueryTest**
            
            - **Code:** [RestaurantQueryTest Class](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/test/java/G26/Project/Model/Search/RestaurantQueryTest.java), entire file for the [RestaurantQuery Class](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/src/app/src/main/java/G26/Project/Model/Search/RestaurantQuery.java), entire file.
            - **Number of test cases:** 9
            - **Code coverage:** 100%
            - **Types of tests created and descriptions:**
                1. **City Retrieval Test (getCity)**: Validates if the cities set in the RestaurantQuery match the expected list of cities.
                2. **Category Retrieval Test (getCategory)**: Ensures the categories/types retrieved from the query match the expected list of restaurant types.
                3. **Restaurant Names Setting Test (setRestaurantNames)**: Tests the **`setRestaurantNames`** method by setting a new list of restaurant names and checking if they were set correctly in the RestaurantQuery object.
                4. **ToString Method Test (testToString)**: Validates that the **`toString`** method of the RestaurantQuery class returns a correctly formatted string representation of the object's state.
                5. **Equality Test (testEquals)**: Compares two RestaurantQuery objects with identical values to ensure they are considered equal using the **`equals`** method.
                6. **HashCode Test (testHashCode)**: Compares the hash codes of two RestaurantQuery objects with identical values to ensure they are the same.
                7. *Other getter methods*: The comments hint at more tests related to the other getter methods of the RestaurantQuery class, but these methods are not explicitly shown.
                8. *Other setter methods*: Similarly, the comments suggest there are other tests related to setter methods, but they are not shown in the provided code.
                9. **Teardown and Setup**: The **`setUp`** and **`tearDown`** methods, although not tests are critical in ensuring that each test case starts with a fresh RestaurantQuery object and that resources are appropriately cleaned up after each test.

## Team Management

### Meetings Records

- *[Team Meeting 1](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/items/Meeting%20Records/Week7/Meeting%20Record-Week7_1.md)*
- *[Team Meeting 2](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/items/Meeting%20Records/Week7/Meeting%20Record-Week7_2.md)*
- *[Team Meeting 3](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/items/Meeting%20Records/Week7/Meeting%20Record-Week7_3.md)*
- [Team Meeting 4](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/items/Meeting%20Records/Week7/Meeting%20Record-Week7_4.md)
- [Team Meeting 5](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/items/Meeting%20Records/Week8/meeting2.md)
- [Team Meeting 6](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/items/Meeting%20Records/Week8/Meeting%20Record-Week8_2.md)
- [Team Meeting 7](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/items/Meeting%20Records/Week8/Meeting%20Record-Week8_3.md)
- [Team Meeting 8](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/items/Meeting%20Records/Week9/meeting3.md)
- [Team Meeting 9](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/items/Meeting%20Records/Week9/Meeting%20Record-Week9_1.md)
- [Team Meeting 10](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/items/Meeting%20Records/Week9/Meeting%20Record-Week9_2.md)
- [Team Meeting 11](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/items/Meeting%20Records/Week9/Meeting%20Record-Week9_3.md)
- [Team Meeting 12](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/items/Meeting%20Records/Week9/Meeting%20Record-Week9_4.md)
- [Team Meeting 13](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/items/Meeting%20Records/Week10/meeting4.md)
- [Team Meeting 14](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/items/Meeting%20Records/Week10/Meeting%20Record-Week10_1.md)
- [Team Meeting 15](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/items/Meeting%20Records/Week10/Meeting%20Record-Week10_2.md)
- [Team Meeting 16](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/items/Meeting%20Records/Week10/Meeting%20Record-Week10_3.md)
- [Team Meeting 17](https://gitlab.cecs.anu.edu.au/u7574419/ga-23s2/-/blob/main/items/Meeting%20Records/Week10/Meeting%20Record-Week10_4.md)

---

### **Conflict Resolution Protocol**

**1. Identification of the Conflict:**

- **Recognition:** All team members are encouraged to promptly identify and acknowledge when a conflict or issue arises that may impact the team or project's progression.
- **Initial Documentation:** The identified conflict should be documented with specifics, including the parties involved, the nature of the conflict, and any initial impact on project deliverables or timelines.

**2. Open Communication:**

- **Prompt Discussion:** A meeting should be promptly scheduled, ensuring all relevant parties' participation, to openly discuss the conflict. This meeting should occur in a safe space that encourages honest communication.
- **Active Listening:** During discussions, all members must practice active listening, allowing each party to express their perspectives fully before responding.
- **Respectful Engagement:** All communication should remain respectful, focused on resolving the issue rather than personal grievances.

**3. Collaborative Solution Building:**

- **Creative Brainstorming:** All parties should engage in a creative process to propose potential solutions, considering the benefits and drawbacks of each.
- **Willingness to Compromise:** Resolution often requires compromise; thus, flexibility and openness to alternative solutions are crucial.
- **External Consultation:** If a resolution is not readily achievable, the team should not hesitate to seek advice from a mentor, advisor, or appointed neutral third party.

**4. Action and Implementation:**

- **Clear Action Plan:** Once consensus is reached, a clear action plan detailing the resolution steps, responsible parties, and any adjusted deadlines should be established.
- **Progress Monitoring:** Regular check-ins should be scheduled to assess the resolution's effectiveness and ensure adherence to the new plan.

**5. Reflection and Continuous Improvement:**

- **Constructive Feedback:** Post-resolution, feedback should be solicited from all parties to understand the conflict resolution process's effectiveness.
- **Adaptive Learning:** Insights and lessons learned should be actively incorporated into future team practices and conflict resolution approaches.

**6. Formal Documentation and Closure:**

- **Comprehensive Documentation:** The conflict, resolution process, agreed-upon outcomes, and any subsequent adjustments to project plans should be formally documented for future reference.
- **Acknowledgment of Resolution:** A final meeting should be held to formally acknowledge the conflict's resolution, reaffirm team commitments, and officially close the matter, fostering a sense of collective closure and readiness to move forward.

**Special Considerations:**

- **Contingency for Unforeseen Circumstances:** In the event of unexpected incidents, such as a team member falling ill, a contingency plan should be pre-established and include steps like redistributing responsibilities among capable team members or negotiating deadline extensions.
- **Persistent Issues and Deadlines:** For conflicts arising from missed deadlines or consistent issues, a more stringent review process might be necessary, potentially involving project supervisors or higher authority to ensure accountability and determine necessary corrective measures.

## Requirement Document
[DineFinder - Requirement Document - D2.0](https://www.notion.so/DineFinder-Requirement-Document-D2-0-1d5dce63f748459180174486566b69e1?pvs=21)
