(TeX-add-style-hook
 "informe"
 (lambda ()
   (TeX-add-to-alist 'LaTeX-provided-class-options
                     '(("article" "12pt" "letterpaper")))
   (TeX-add-to-alist 'LaTeX-provided-package-options
                     '(("adjustbox" "export") ("xcolor" "table")))
   (TeX-run-style-hooks
    "latex2e"
    "article"
    "art12"
    "graphicx"
    "adjustbox"
    "flafter"
    "hyperref"
    "polyglossia"
    "subfiles"
    "xcolor"
    "fancyhdr"
    "listings"
    "tabularx"
    "array"
    "color"
    "colortbl"
    "lineno"
    "subfig")
   (LaTeX-add-labels
    "introduction"
    "decissisions"
    "enumeracions"
    "interfaces"
    "citizen"
    "dni"
    "encyptedData"))
 :latex)

