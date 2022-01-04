(TeX-add-style-hook
 "results"
 (lambda ()
   (TeX-add-to-alist 'LaTeX-provided-class-options
                     '(("subfiles" "../informe.tex")))
   (TeX-run-style-hooks
    "latex2e"
    "subfiles"
    "subfiles10"))
 :latex)

