# README

Web service for City:Cult's backend.

Generate HTML documentation on Windows:

```
> pip install --user tox>=4.0.0
> python -m tox run -e docs
```

Generate HTML documentation on Linux:

```
$ pip3 install tox>=4.0.0
$ tox run -e docs
```

Generated HTML is located at `build/docs/html`.
