# Contributing

This mod is currently built using the Fabric API, targeting Minecraft version `1.21.5`.

There's obviously not much here right now, and so any changes are definitely welcome.

## Pull request submissions

Please don't send massive pull requests! They are horrible to read through. Also, if you are going to
make a new method, function, or class, please include comments that indicate the desired behavior of
that specific member. Java and IntelliJ use [JSDoc 3](https://jsdoc.app/about-getting-started), so please
make use of this.

## Dependencies

In order to be able to develop this project, you need two things.


### Java
The first thing is Java - this is a base requirement because Minecraft is written
in Java. You can find the download link on the Java website [here](https://java.com).

### NodeJS
The second thing you'll need is NodeJS. This isn't a requirement for Minecraft
development, but for commit message linting. I personally like to have organized
commit messages, so the `husky` and `commitlint` packages will enforce the Conventional
Commits standard. As a result, you'll need to have an installation of NodeJS.

If you're using Windows, I highly recommend the usage of `fnm`, which can be easily
installed by running this on the commandline:
```
winget install Schniz.fnm
```
After that's finished, you'll want to restart your terminal. Then, to install NodeJS
itself, you'll need to run these commands:
```
fnm install --lts
fnm use lts-latest
```
That should complete NodeJS installation - now you'll need the packages. Run the command
`npm install` from within the repository's directory and you should be all set!

### How do I commit?
Conventional Commits has the format `<tag>(scope): <message>`. The `tag` and `message` are
always required. The most common tags that we would use are `feat`, `fix`, `chore`, and `build`.

Without going over the entire standard, we always have a three digit version number - for example,
something like `v4.2.5`. In this case, `4` is the major version number, `2` is the minor version,
and `5` is the patch number. In general, `feat` commits will increase the minor version, `fix` commits
will increase the patch number, and any commit with `BREAKING CHANGE` in the footer will increase the
major version. The `build` and `chore` commits don't change versions, and are used for super small changes
that won't affect the overall meaning of the code.

If you'd like to know more, you can find more information about Conventional Commits on their website
[here](https://www.conventionalcommits.org/en/v1.0.0/).

## How to build the project

I will assume that you're using the community version of [IntelliJ IDEA](https://www.jetbrains.com/idea/),
as the Community Edition is a free download. I highly recommend using this.

In the top right corner of the IDE, you'll find a green arrow button and a dropdown list. The dropdown
will allow you to choose a run configuration, in this case the Minecraft server or client. Pick one of
these configurations, and click run. It will build the mod and boot up an instance of the server/client
with the mod already loaded.

# Enjoy!

That's all you should need to get started with contributing to this mod!