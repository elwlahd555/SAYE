> Team 6
> 
> BigData PJT

# Sing About Your Emotion (SAYE) 🎶🎶🎶

| 😘 SAYE :: Sing About Your Emotion, 당신의 감정을 노래하다. 자세한 기획 개요는 [여기서](./docs/assets/README.md) 보실 수 있습니다.


<div align="center">
  <br>
  <a href="https://j4d106.p.ssafy.io/"><img src="./docs/assets/saye.png" alt="SAYE" width="200"></a>
</div>

  <p align="center">
    <a href="./">한국어</a> •
    <a href="./docs/assets/translate/README.md">English</a> 
  </p>

---


<h4 align="center">Just write your feelings today. Just listen to the music for you </h4>

<p align="center">

  <a href="https://vuejs.org/">
    <img src="https://img.shields.io/badge/vue-2.6.11-%234FC08D?style=plastic&logo=Vue.js">
  </a>
  <a href="https://vuetifyjs.com/en/">
      <img src="https://img.shields.io/badge/vuetify-2.2.11-%234FC08D?style=plastic&logo=vuetify">
  </a>
    <a href="https://spring.io/projects/spring-boot">
      <img src="https://img.shields.io/badge/spring_boot-2.4.2-%236DB33F?style=plastic&logo=Spring">
  </a>
    <a href="https://www.mysql.com/">
      <img src="https://img.shields.io/badge/MySQL-8.0-%234479A1?style=plastic&logo=mysql">
  </a>
    <a href="https://www.docker.com/get-started">
      <img src="https://img.shields.io/badge/docker-19.03.8-%236DB33F?color=blue&style=plastic&logo=docker">
  </a>
    <a href="https://www.jenkins.io/">
      <img src="https://img.shields.io/badge/Jenkins-2.263.4-%236DB33F?color=red&style=plastic&logo=Jenkins">
  </a>
  <!--<a href="https://www.paypal.me/AmitMerchant">-->
  <a href="">
    <img src="https://img.shields.io/badge/$-donate-ff69b4.svg?maxAge=2592000&amp;style=flat">
  </a>
</p>

<p align="center">
  <a href="https://j4d106.p.ssafy.io/">사이트 방문</a> •
  <a href="./docs/demo/README.md">데모(demo)</a> 
</p>

![screenshot](./docs/assets/demo/0_introPage.gif)

## 🚩 Table of Contents


- [What's SAYE?](#-whats-saye)
- [Features](#-features)
- [Examples](#-examples)
- [Browser Support](#-browser-support)
- [Packages](#-packages)
- [Pull Request Steps](#-pull-request-steps)
- [Contributing](#-contributing)
- [TOAST UI Family](#-toast-ui-family)
- [References](#-references)
- [License](#-license)


## 🤖 What's SAYE?

TOAST UI Editor provides **Markdown mode** and **WYSIWYG mode**. Depending on the type of use you want like production of *Markdown* or maybe to just edit the *Markdown*. The TOAST UI Editor can be helpful for both the usage. It offers **Markdown mode** and **WYSIWYG mode**, which can be switched any point in time.

### Productive Markdown Mode

![markdown](https://user-images.githubusercontent.com/18183560/84381972-d3c62600-ac24-11ea-99e2-9640b0a2bfe8.png)

**CommonMark + GFM Specifications**

Today *CommonMark* is the de-facto *Markdown* standard. *GFM (GitHub Flavored Markdown)* is another popular specification based on *CommonMark* - maintained by *GitHub*, which is the *Markdown* mostly used. TOAST UI Editor follows both [*CommonMark*](http://commonmark.org/) and [*GFM*](https://github.github.com/gfm/) specifications. Write documents with ease using productive tools provided by TOAST UI Editor and you can easily open the produced document wherever the specifications are supported.

* **Live Preview** : Edit Markdown while keeping an eye on the rendered HTML. Your edits will be applied immediately.
* **Scroll Sync** : Synchronous scrolling between Markdown and Preview. You don't need to scroll through each one separately.
* **Auto Indent** : The cursor will always be where you want it to be.
* **Syntax Highlight** : You can check broken Markdown syntax immediately.

### Easy WYSIWYG Mode

![wysiwyg](https://user-images.githubusercontent.com/18183560/84381978-d88ada00-ac24-11ea-8655-3208cb46a8ae.png)

* **Table** : Through the context menu of the table, you can add or delete columns or rows of the table, and you can also arrange text in cells.
* **Code Block Editor** : The code block area can be edited through the layer popup editor.

### Usability Common UI

![UI](https://user-images.githubusercontent.com/18183560/84381975-d6c11680-ac24-11ea-9816-9cf2aecbdf06.gif)

* **Toolbar** : Through the toolbar, you can style or add elements to the document you are editing.
* **Copy and Paste** : Paste anything from browser, screenshot, excel, powerpoint, etc.

### Use of Various Extended Functions - Plugins

![plugin](https://user-images.githubusercontent.com/18183560/84381966-cf9a0880-ac24-11ea-9bb4-1e8a8ca186de.png)

CommonMark and GFM are great, but we often need more abstraction. The TOAST UI Editor comes with powerful **Plugins** in compliance with the Markdown syntax.

**Five basic plugins** are provided as follows, and can be downloaded and used with npm.

* [**`chart`**](https://github.com/nhn/tui.editor/tree/master/plugins/chart) : A code block marked as a 'chart' will render [TOAST UI Chart](https://github.com/nhn/tui.chart).
* [**`code-syntax-highlight`**](https://github.com/nhn/tui.editor/tree/master/plugins/code-syntax-highlight) : Highlight the code block area corresponding to the language provided by [highlight.js](https://highlightjs.org/).
* [**`color-syntax`**](https://github.com/nhn/tui.editor/tree/master/plugins/color-syntax) : 
Using [TOAST UI ColorPicker](https://github.com/nhn/tui.color-picker), you can change the color of the editing text with the GUI.
* [**`table-merged-cell`**](https://github.com/nhn/tui.editor/tree/master/plugins/table-merged-cell) : 
You can merge columns of the table header and body area.
* [**`uml`**](https://github.com/nhn/tui.editor/tree/master/plugins/uml) : A code block marked as an 'uml' will render [UML diagrams](http://plantuml.com/screenshot).

## 🎨 Features

* [**Viewer**](https://github.com/nhn/tui.editor/tree/master/apps/editor/docs/viewer.md) : Supports a mode to display only markdown data without an editing area.
* [**Internationalization (i18n)**](https://github.com/nhn/tui.editor/tree/master/apps/editor/docs/i18n.md) : Supports English, Dutch, Korean, Japanese, Chinese, Spanish, German, Russian, French, Ukrainian, Turkish, Finnish, Czech, Arabic, Polish, Galician, Swedish, Italian, Norwegian, Croatian, Portuguese + language and you can extend.

## 🐾 Examples

* [Basic](https://nhn.github.io/tui.editor/latest/tutorial-example01-editor-basic)
* [Viewer](https://nhn.github.io/tui.editor/latest/tutorial-example04-viewer)
* [Using All Plugins](https://nhn.github.io/tui.editor/latest/tutorial-example15-editor-with-all-plugins)
* [Creating the User's Plugin](https://nhn.github.io/tui.editor/latest/tutorial-example17-creating-plugin)
* [Customizing the Toobar Buttons](https://nhn.github.io/tui.editor/latest/tutorial-example19-customizing-toolbar-buttons)
* [Internationalization (i18n)](https://nhn.github.io/tui.editor/latest/tutorial-example20-i18n)

Here are more [examples](https://nhn.github.io/tui.editor/latest/tutorial-example01-editor-basic) and play with TOAST UI Editor!


## 🌏 Browser Support

| <img src="https://user-images.githubusercontent.com/1215767/34348387-a2e64588-ea4d-11e7-8267-a43365103afe.png" alt="Chrome" width="16px" height="16px" /> Chrome | <img src="https://user-images.githubusercontent.com/1215767/34348590-250b3ca2-ea4f-11e7-9efb-da953359321f.png" alt="IE" width="16px" height="16px" /> Internet Explorer | <img src="https://user-images.githubusercontent.com/1215767/34348380-93e77ae8-ea4d-11e7-8696-9a989ddbbbf5.png" alt="Edge" width="16px" height="16px" /> Edge | <img src="https://user-images.githubusercontent.com/1215767/34348394-a981f892-ea4d-11e7-9156-d128d58386b9.png" alt="Safari" width="16px" height="16px" /> Safari | <img src="https://user-images.githubusercontent.com/1215767/34348383-9e7ed492-ea4d-11e7-910c-03b39d52f496.png" alt="Firefox" width="16px" height="16px" /> Firefox |
| :---------: | :---------: | :---------: | :---------: | :---------: |
| Yes | 10+ | Yes | Yes | Yes |



## 📦 Packages (M)

### Frontend

| Name | Description |
| --- | --- |
| [`@toast-ui/editor`](https://github.com/nhn/tui.editor/tree/master/apps/editor) | Plain JavaScript component |

### Backend

| Name | Description |
| --- | --- |
| [`@toast-ui/jquery-editor`](https://github.com/nhn/tui.editor/tree/master/apps/jquery-editor) | [jQuery](https://jquery.com/) wrapper component |
| [`@toast-ui/react-editor`](https://github.com/nhn/tui.editor/tree/master/apps/react-editor) | [React](https://reactjs.org/) wrapper component |
| [`@toast-ui/vue-editor`](https://github.com/nhn/tui.editor/tree/master/apps/vue-editor) | [Vue](https://vuejs.org/) wrapper component |

### SAYE UI Editor's Plugins

| Name | Description |
| --- | --- |
| [`@toast-ui/editor-plugin-chart`](https://github.com/nhn/tui.editor/tree/master/plugins/chart) | Plugin to render chart |
| [`@toast-ui/editor-plugin-code-syntax-highlight`](https://github.com/nhn/tui.editor/tree/master/plugins/code-syntax-highlight) | Plugin to highlight code syntax |
| [`@toast-ui/editor-plugin-color-syntax`](https://github.com/nhn/tui.editor/tree/master/plugins/color-syntax) | Plugin to color editing text |
| [`@toast-ui/editor-plugin-table-merged-cell`](https://github.com/nhn/tui.editor/tree/master/plugins/table-merged-cell) | Plugin to merge table columns |
| [`@toast-ui/editor-plugin-uml`](https://github.com/nhn/tui.editor/tree/master/plugins/uml) | Plugin to render UML |



## 🔧 Pull Request Steps

TOAST UI products are open source, so you can create a pull request(PR) after you fix issues. Run npm scripts and develop yourself with the following process.

### Setup

Fork `master` branch into your personal repository. Clone it to local computer. Install node modules. Before starting development, you should check to see if there are any errors.

```sh
$ git clone https://github.com/{your-personal-repo}/tui.editor.git
$ cd [project-name]
$ npm install
$ npm run setup:libs
$ cd ./apps/editor
$ npm install
$ npm run test
```

### Develop

You can see your code is reflected as soon as you saving the codes by running a server. Don't miss adding test cases and then make green rights.

#### Run webpack-dev-server

``` sh
$ npm run serve
```

#### Run karma

``` sh
$ npm run test
```

### Pull Request

Before creating a PR, test and check for any errors. If there are no errors, then commit and push.

For more information, please refer to the Contributing section.

## 💬 Documents

* [SAYE - Notion](https://www.notion.so/oct14jh/SAYE-Sing-About-Your-Emotion-301b283ab8ca4fb7b49647eb99e98a20)


## 👥 SAYE Family

- [김지현](https://github.com/)
- [김동빈](https://github.com/)
- [김태진](https://github.com/)
- [김호빈](https://github.com/)
- [김연정](https://github.com/)


## 🚀 References

- [Markdownify - README.md](https://github.com/amitmerchant1990/electron-markdownify#related)
- [TOAST UI Editor - README.md](https://github.com/nhn/tui.editor#readme)


## 📜 License

This software is licensed under the [MIT](.docs/LICENSE) © [SSAFY](https://www.ssafy.com/ksp/jsp/swp/swpMain.jsp).