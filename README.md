<!-- DEMO -->
<p align="center">
  <img src="./media/bigUML-demo.gif" alt="Demo" />
</p>

<!-- BADGES -->
<p align="center">
  <a target="_blank" href="https://marketplace.visualstudio.com/items?itemName=BIGModelingTools.umldiagram">
    <img alt="Visual Studio Marketplace Installs" src="https://img.shields.io/visual-studio-marketplace/i/BIGModelingTools.umldiagram?color=9cf&style=for-the-badge&label=VS%20Code%20Installs" height="20"/>
  </a>
  <a target="_blank" href="https://marketplace.visualstudio.com/items?itemName=BIGModelingTools.umldiagram">
    <img alt="Visual Studio Marketplace Version" src="https://img.shields.io/visual-studio-marketplace/v/BIGModelingTools.umldiagram?style=for-the-badge&label=VS%20Code%20Version" height="20"/>
  </a>
</p>

<br>

<!-- LOGO -->
<img align="left" src="./client/packages/uml-vscode-integration/extension/resources/logo.png" alt="Logo" width="150" height="150" />

<!-- TITLE -->
<h1 align="center">&emsp;bigUML Modeling Tool</h1>

<p align="center">
  &emsp;<strong>Editing UML diagrams in VS Code and developing custom integrations for your IDE of choice is now possible.</strong>
</p>

<br>

- For VS Code users, the project is already distributed in the [VS Code marketplace](https://marketplace.visualstudio.com/items?itemName=BIGModelingTools.umldiagram) and can be installed directly from VS Code.

- This project enables developers to create their own editors by utilizing [GLSP](https://www.eclipse.org/glsp/), where the UML specification is/will be implemented.

---

</br>
</br>

<div align="center">

**[UML](#uml) •
[DOCUMENTATION](#documentation) •
[DEVELOPMENT SETUP](#development-setup) •
[CONTRIBUTING](#contributing) •
[LICENSE](#license) •
[MODELING TOOLS](#modeling-tools)**

</div>

</br>

## UML

The UML 2 specification currently consists of **7 Structure Diagrams** and **7 Behavior Diagrams**.

<!-- =========================== -->
<details>
<summary><strong>Class Diagram</strong></summary>

<img src="./media/vscode-class.png" alt="Class Diagram" />

<em>Initial implementation provided by: [@haydar-metin](https://github.com/haydar-metin), EclipseSource</em>

- Object Diagram integration by: [@vladfreeze](https://github.com/vladfreeze)

</details>

<!-- =========================== -->
<details>
<summary><strong>Use Case Diagram</strong></summary>

<img src="./media/vscode-use-case.png" alt="Use Case Diagram" />

<em>Initial implementation provided by: [@julia05](https://github.com/julia05), [@JakobD97](https://github.com/JakobD97)</em>

</details>

<!-- =========================== -->
<details>
<summary><strong>Package Diagram</strong></summary>

Coming Soon!

<em>Initial implementation provided by: [@DerYeger](https://github.com/DerYeger), [@mrstexx](https://github.com/mrstexx)</em>

</details>

<!-- =========================== -->
<details>
<summary><strong>State Machine Diagram</strong></summary>

<img src="./media/vscode-state-machine.png" alt="State Machine Diagram" />

<em>Initial implementation provided by: [@granigd](https://github.com/granigd), [@DavidJaeger10](https://github.com/DavidJaeger10), [@sschwantler](https://github.com/sschwantler)</em>

</details>

<!-- =========================== -->
<details>
<summary><strong>Deployment Diagram</strong></summary>

<img src="./media/vscode-deployment.png" alt="Deployment Diagram" />

<em>Initial implementation provided by: [@MZeisler](https://github.com/MZeisler), [@H0oKd](https://github.com/H0oKd)</em>

</details>

<!-- =========================== -->
<details>
<summary><strong>Progress</strong></summary>

### Structure Diagrams

| Class  | Component | Deployment | Object                | Package              | Profile | Composite |
| ------ | --------- | ---------- | --------------------- | -------------------- | ------- | --------- |
| Merged | -         | Merged     | Integrated into Class | Merged, not released | -       | -         |

### Behavior Diagrams

| Use Case | Activity | State Machine | Sequence                 | Communication        | Interaction | Timing |
| -------- | -------- | ------------- | ------------------------ | -------------------- | ----------- | ------ |
| Merged   | -        | Merged        | Contribution in progress | Merged, not released | -           | -      |

</details>

## Documentation

Get started with bigUML, learn fundamentals, explore advanced topics, or go through tutorials with our documentation.

- [Changelog](./CHANGELOG.md)
- [Getting Started](./docs/getting-started.md)
- [Documentation](./docs/README.md)
- [Tutorials](./docs/tutorial/README.md)

## Development Setup

Need help concerning the setup? Then go right to [Requirements](./docs/requirements.md) and continue with [Getting Started](./docs/getting-started.md).

## Contributors ✨

Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tbody>
    <tr>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/aylin-sarioglu"><img src="https://avatars.githubusercontent.com/u/61785275?v=4?s=100" width="100px;" alt="Aylin Sarioğlu"/><br /><sub><b>Aylin Sarioğlu</b></sub></a><br /><a href="https://github.com/borkdominik/bigUML/commits?author=aylin-sarioglu" title="Code">💻</a> <a href="#diagram-aylin-sarioglu" title="Worked on a diagram">↔</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/DavidJaeger10"><img src="https://avatars.githubusercontent.com/u/14921155?v=4?s=100" width="100px;" alt="DavidJaeger10"/><br /><sub><b>DavidJaeger10</b></sub></a><br /><a href="https://github.com/borkdominik/bigUML/commits?author=DavidJaeger10" title="Code">💻</a> <a href="#diagram-DavidJaeger10" title="Worked on a diagram">↔</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/granigd"><img src="https://avatars.githubusercontent.com/u/14920972?v=4?s=100" width="100px;" alt="Dominik Granig"/><br /><sub><b>Dominik Granig</b></sub></a><br /><a href="https://github.com/borkdominik/bigUML/commits?author=granigd" title="Code">💻</a> <a href="#diagram-granigd" title="Worked on a diagram">↔</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/haydar-metin"><img src="https://avatars.githubusercontent.com/u/13104167?v=4?s=100" width="100px;" alt="Haydar Metin"/><br /><sub><b>Haydar Metin</b></sub></a><br /><a href="https://github.com/borkdominik/bigUML/commits?author=haydar-metin" title="Code">💻</a> <a href="#maintenance-haydar-metin" title="Maintenance">🚧</a> <a href="#diagram-haydar-metin" title="Worked on a diagram">↔</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/JakobD97"><img src="https://avatars.githubusercontent.com/u/57354440?v=4?s=100" width="100px;" alt="JakobD97"/><br /><sub><b>JakobD97</b></sub></a><br /><a href="https://github.com/borkdominik/bigUML/commits?author=JakobD97" title="Code">💻</a> <a href="#diagram-JakobD97" title="Worked on a diagram">↔</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://jan-mueller.at/"><img src="https://avatars.githubusercontent.com/u/7950094?v=4?s=100" width="100px;" alt="Jan Müller"/><br /><sub><b>Jan Müller</b></sub></a><br /><a href="https://github.com/borkdominik/bigUML/commits?author=DerYeger" title="Code">💻</a> <a href="#diagram-DerYeger" title="Worked on a diagram">↔</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/deweiiss"><img src="https://avatars.githubusercontent.com/u/73828363?v=4?s=100" width="100px;" alt="Julian Weiß"/><br /><sub><b>Julian Weiß</b></sub></a><br /><a href="https://github.com/borkdominik/bigUML/commits?author=deweiiss" title="Code">💻</a></td>
    </tr>
    <tr>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/MZeisler"><img src="https://avatars.githubusercontent.com/u/45333967?v=4?s=100" width="100px;" alt="MZeisler"/><br /><sub><b>MZeisler</b></sub></a><br /><a href="https://github.com/borkdominik/bigUML/commits?author=MZeisler" title="Code">💻</a> <a href="#diagram-MZeisler" title="Worked on a diagram">↔</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/H0oKd"><img src="https://avatars.githubusercontent.com/u/127870934?v=4?s=100" width="100px;" alt="Manuel Hude"/><br /><sub><b>Manuel Hude</b></sub></a><br /><a href="https://github.com/borkdominik/bigUML/commits?author=H0oKd" title="Code">💻</a> <a href="#diagram-H0oKd" title="Worked on a diagram">↔</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/sschwantler"><img src="https://avatars.githubusercontent.com/u/52577060?v=4?s=100" width="100px;" alt="Simon Schwantler"/><br /><sub><b>Simon Schwantler</b></sub></a><br /><a href="https://github.com/borkdominik/bigUML/commits?author=sschwantler" title="Code">💻</a> <a href="#diagram-sschwantler" title="Worked on a diagram">↔</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/mrstexx"><img src="https://avatars.githubusercontent.com/u/18032955?v=4?s=100" width="100px;" alt="Stefan Miljevic"/><br /><sub><b>Stefan Miljevic</b></sub></a><br /><a href="https://github.com/borkdominik/bigUML/commits?author=mrstexx" title="Code">💻</a> <a href="#diagram-mrstexx" title="Worked on a diagram">↔</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/vladfreeze"><img src="https://avatars.githubusercontent.com/u/58890859?v=4?s=100" width="100px;" alt="Vlad Vifor"/><br /><sub><b>Vlad Vifor</b></sub></a><br /><a href="https://github.com/borkdominik/bigUML/commits?author=vladfreeze" title="Code">💻</a> <a href="#diagram-vladfreeze" title="Worked on a diagram">↔</a></td>
      <td align="center" valign="top" width="14.28%"><a href="http://model-engineering.info/"><img src="https://avatars.githubusercontent.com/u/60790671?v=4?s=100" width="100px;" alt="borkdominik"/><br /><sub><b>borkdominik</b></sub></a><br /><a href="#projectManagement-borkdominik" title="Project Management">📆</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/julia05"><img src="https://avatars.githubusercontent.com/u/23345501?v=4?s=100" width="100px;" alt="julia05"/><br /><sub><b>julia05</b></sub></a><br /><a href="https://github.com/borkdominik/bigUML/commits?author=julia05" title="Code">💻</a> <a href="#diagram-julia05" title="Worked on a diagram">↔</a></td>
    </tr>
  </tbody>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

Contributions of any kind are welcome! Do not hesitate to report a bug or to request a feature. Feel free to [open Issues](./issues) or submit PRs.

If you like our work, please feel free to [buy us a coffee](https://www.buymeacoffee.com/bigERtool) ☕️

<a href="https://www.buymeacoffee.com/bigERtool" target="_blank">
  <img src="https://www.buymeacoffee.com/assets/img/custom_images/yellow_img.png" alt="Logo" >
</a>

## License

The project is distributed under the [MIT](https://github.com/borkdominik/bigUML/blob/main/LICENSE) License. See [License](https://github.com/borkdominik/bigUML/blob/main/LICENSE) for more details.

</br>
</br>
</br>

<div align="center">

# Modeling Tools

</div>

<p align="center">
  Checkout our other cool tools.
</p>

</br>

<p align="center">
  <img src="https://raw.githubusercontent.com/borkdominik/bigER/main/extension/media/logo.png" alt="Logo" width="150" height="150" />
</p>

<p align="center">
  <b>Open-source ER modeling tool for VS Code supporting hybrid, textual- and graphical editing, multiple notations, and SQL code generation!</b></br>
  <sub><a href="https://marketplace.visualstudio.com/items?itemName=BIGModelingTools.erdiagram">➜ Download the VS Code Extension</a><sub>
</p>

<p align="center">
  <img src="https://user-images.githubusercontent.com/39776671/197230584-f045bee2-0d5a-4120-b0cf-3ad7ae7675d8.gif" alt="Demo" width="800" />
</p>
