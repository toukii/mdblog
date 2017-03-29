# git

## git submodule

```
git init
git submodule init
git submodule add http://github.com/datc/centos.git centos
git submodule add http://github.com/datc/pretty.git pretty
```

`rm -rf centos`
`rm -rf pretty`

```
git submodule update --init
// or
git submodule update --checkout
```